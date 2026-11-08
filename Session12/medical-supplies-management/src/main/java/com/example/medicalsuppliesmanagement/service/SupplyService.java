package com.example.medicalsuppliesmanagement.service;

import com.example.medicalsuppliesmanagement.dto.request.CreateSupplyRequest;
import com.example.medicalsuppliesmanagement.dto.response.SupplyResponse;
import com.example.medicalsuppliesmanagement.entity.Supply;
import com.example.medicalsuppliesmanagement.exception.BadRequestException;
import com.example.medicalsuppliesmanagement.repository.SupplyRepository;
import com.example.medicalsuppliesmanagement.dto.request.UpdateSupplyRequest;
import com.example.medicalsuppliesmanagement.exception.ResourceNotFoundException;
import com.example.medicalsuppliesmanagement.dto.request.StockAmountRequest;
import com.example.medicalsuppliesmanagement.entity.InventoryTransaction;
import com.example.medicalsuppliesmanagement.enums.TransactionType;
import com.example.medicalsuppliesmanagement.repository.InventoryTransactionRepository;
import com.example.medicalsuppliesmanagement.dto.response.DailyExportResponse;
import com.example.medicalsuppliesmanagement.dto.response.TopExportResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class SupplyService {

    private static final Logger historyLogger =
            LoggerFactory.getLogger("HISTORY_LOGGER");

    private final SupplyRepository supplyRepository;
    private final InventoryTransactionRepository transactionRepository;

    public SupplyService(
            SupplyRepository supplyRepository,
            InventoryTransactionRepository transactionRepository
    ) {
        this.supplyRepository = supplyRepository;
        this.transactionRepository = transactionRepository;
    }

    public SupplyResponse createSupply(CreateSupplyRequest request) {

        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new BadRequestException("Tên vật tư không được để trống");
        }

        Supply supply = new Supply();

        supply.setName(request.getName().trim());
        supply.setSpecification(request.getSpecification());
        supply.setProvider(request.getProvider());
        supply.setUnit(request.getUnit());

        // Luôn bằng 0 khi tạo mới
        supply.setQuantity(0);
        supply.setIsDeleted(false);

        Supply savedSupply = supplyRepository.save(supply);

        log.info(
                "Đã tạo mới vật tư: {} với ID: {}",
                savedSupply.getName(),
                savedSupply.getId()
        );

        return new SupplyResponse(
                savedSupply.getId(),
                savedSupply.getName(),
                savedSupply.getSpecification(),
                savedSupply.getProvider(),
                savedSupply.getUnit(),
                savedSupply.getQuantity()
        );
    }

    public SupplyResponse updateSupply(Long id, UpdateSupplyRequest request) {

        if (request.getId() != null || request.getQuantity() != null) {

            log.warn(
                    "Client gửi dữ liệu không được phép khi cập nhật vật tư ID {}",
                    id
            );

            throw new BadRequestException(
                    "Không được phép cập nhật id hoặc quantity"
            );
        }

        if (request.getUnit() != null) {

            log.warn(
                    "Client cố cập nhật unit của vật tư ID {}",
                    id
            );

            throw new BadRequestException(
                    "Chỉ được phép cập nhật name, specification và provider"
            );
        }

        Supply supply = supplyRepository
                .findByIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy vật tư với ID: " + id
                        )
                );

        if (request.getName() != null) {
            if (request.getName().trim().isEmpty()) {
                throw new BadRequestException(
                        "Tên vật tư không được để trống"
                );
            }

            supply.setName(request.getName().trim());
        }

        if (request.getSpecification() != null) {
            supply.setSpecification(request.getSpecification());
        }

        if (request.getProvider() != null) {
            supply.setProvider(request.getProvider());
        }

        Supply updatedSupply = supplyRepository.save(supply);

        log.info(
                "Đã cập nhật vật tư ID: {}",
                updatedSupply.getId()
        );

        return new SupplyResponse(
                updatedSupply.getId(),
                updatedSupply.getName(),
                updatedSupply.getSpecification(),
                updatedSupply.getProvider(),
                updatedSupply.getUnit(),
                updatedSupply.getQuantity()
        );
    }

    public void deleteSupply(Long id) {

        Supply supply = supplyRepository
                .findByIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy vật tư với ID: " + id
                        )
                );

        supply.setIsDeleted(true);

        supplyRepository.save(supply);

        log.info(
                "Đã xóa mềm vật tư: {} với ID: {}",
                supply.getName(),
                supply.getId()
        );
    }

    public List<SupplyResponse> getAllActiveSupplies() {

        log.info("Lấy danh sách vật tư đang hoạt động");

        List<Supply> supplies = supplyRepository.findByIsDeletedFalse();

        log.debug(
                "Đã truy vấn {} vật tư đang hoạt động",
                supplies.size()
        );

        return supplies.stream()
                .map(supply -> new SupplyResponse(
                        supply.getId(),
                        supply.getName(),
                        supply.getSpecification(),
                        supply.getProvider(),
                        supply.getUnit(),
                        supply.getQuantity()
                ))
                .toList();
    }

    public List<SupplyResponse> searchSupplies(String name) {

        log.info("Tìm kiếm vật tư với từ khóa: {}", name);

        List<Supply> supplies =
                supplyRepository
                        .findByNameContainingIgnoreCaseAndIsDeletedFalse(name);

        if (supplies.isEmpty()) {
            log.info(
                    "Không tìm thấy vật tư nào khớp với từ khóa: {}",
                    name
            );
        }

        return supplies.stream()
                .map(supply -> new SupplyResponse(
                        supply.getId(),
                        supply.getName(),
                        supply.getSpecification(),
                        supply.getProvider(),
                        supply.getUnit(),
                        supply.getQuantity()
                ))
                .toList();
    }

    @Transactional
    public SupplyResponse exportSupply(
            Long id,
            StockAmountRequest request
    ) {

        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new BadRequestException(
                    "Số lượng xuất phải lớn hơn 0"
            );
        }

        Supply supply = supplyRepository
                .findByIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy vật tư với ID: " + id
                        )
                );

        int currentQuantity = supply.getQuantity();
        int amount = request.getAmount();

        if (currentQuantity < amount) {

            log.error(
                    "Thất bại khi xuất kho ID {}: Yêu cầu {}, hiện có {}",
                    id,
                    amount,
                    currentQuantity
            );

            throw new BadRequestException(
                    "Số lượng tồn kho không đủ để xuất"
            );
        }

        supply.setQuantity(currentQuantity - amount);

        Supply updatedSupply = supplyRepository.save(supply);

        InventoryTransaction transaction =
                new InventoryTransaction();

        transaction.setSupply(updatedSupply);
        transaction.setType(TransactionType.EXPORT);
        transaction.setAmount(amount);

        transactionRepository.save(transaction);

        log.info(
                "Đã xuất kho vật tư ID {}, số lượng {}",
                id,
                amount
        );

        return new SupplyResponse(
                updatedSupply.getId(),
                updatedSupply.getName(),
                updatedSupply.getSpecification(),
                updatedSupply.getProvider(),
                updatedSupply.getUnit(),
                updatedSupply.getQuantity()
        );
    }

    @Transactional
    public SupplyResponse importSupply(
            Long id,
            StockAmountRequest request
    ) {

        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new BadRequestException(
                    "Số lượng nhập phải lớn hơn 0"
            );
        }

        Supply supply = supplyRepository
                .findByIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy vật tư với ID: " + id
                        )
                );

        int oldQuantity = supply.getQuantity();
        int amount = request.getAmount();

        supply.setQuantity(oldQuantity + amount);

        Supply updatedSupply = supplyRepository.save(supply);

        InventoryTransaction transaction =
                new InventoryTransaction();

        transaction.setSupply(updatedSupply);
        transaction.setType(TransactionType.IMPORT);
        transaction.setAmount(amount);

        transactionRepository.save(transaction);

        log.info(
                "Đã nhập kho vật tư ID {}, số lượng {}",
                id,
                amount
        );

        historyLogger.info(
                "Nhập kho ID {}, số lượng [+{}], tồn cũ [{}]",
                id,
                amount,
                oldQuantity
        );

        return new SupplyResponse(
                updatedSupply.getId(),
                updatedSupply.getName(),
                updatedSupply.getSpecification(),
                updatedSupply.getProvider(),
                updatedSupply.getUnit(),
                updatedSupply.getQuantity()
        );
    }

    public List<DailyExportResponse> getDailyExportStatistics() {

        long startTime = System.currentTimeMillis();

        log.info(
                "Bắt đầu thống kê xuất kho trong ngày lúc {}",
                LocalDateTime.now()
        );

        LocalDate today = LocalDate.now();

        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime startOfNextDay = today.plusDays(1).atStartOfDay();

        List<DailyExportResponse> result =
                transactionRepository.findDailyExportStatistics(
                        TransactionType.EXPORT,
                        startOfDay,
                        startOfNextDay
                );

        long elapsed = System.currentTimeMillis() - startTime;

        log.info(
                "Hoàn thành thống kê xuất kho lúc {}, thời gian xử lý {} ms",
                LocalDateTime.now(),
                elapsed
        );

        return result;
    }

    public TopExportResponse getTopExportSupply() {

        log.info("Bắt đầu thống kê vật tư xuất kho nhiều nhất");

        List<Object[]> results =
                transactionRepository.findTopExport(
                        TransactionType.EXPORT
                );

        if (results.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Chưa có dữ liệu giao dịch để thống kê"
            );
        }

        Object[] top = results.get(0);

        String supplyName = (String) top[0];
        Long totalQuantity = (Long) top[1];

        log.info(
                "Vật tư xuất kho nhiều nhất: {}, tổng số lượng: {}",
                supplyName,
                totalQuantity
        );

        return new TopExportResponse(
                supplyName,
                totalQuantity
        );
    }
}