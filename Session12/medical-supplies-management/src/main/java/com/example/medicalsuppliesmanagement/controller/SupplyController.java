package com.example.medicalsuppliesmanagement.controller;

import com.example.medicalsuppliesmanagement.dto.request.CreateSupplyRequest;
import com.example.medicalsuppliesmanagement.dto.response.SupplyResponse;
import com.example.medicalsuppliesmanagement.response.ApiResponse;
import com.example.medicalsuppliesmanagement.service.SupplyService;
import com.example.medicalsuppliesmanagement.dto.request.UpdateSupplyRequest;
import com.example.medicalsuppliesmanagement.dto.request.StockAmountRequest;
import com.example.medicalsuppliesmanagement.dto.response.DailyExportResponse;
import com.example.medicalsuppliesmanagement.dto.response.TopExportResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/supplies")
public class SupplyController {

    private final SupplyService supplyService;

    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SupplyResponse>> createSupply(
            @RequestBody CreateSupplyRequest request
    ) {

        SupplyResponse supply = supplyService.createSupply(request);

        ApiResponse<SupplyResponse> response = new ApiResponse<>(
                "SUCCESS",
                201,
                "Tạo vật tư thành công",
                supply
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplyResponse>> updateSupply(
            @PathVariable Long id,
            @RequestBody UpdateSupplyRequest request
    ) {

        SupplyResponse supply = supplyService.updateSupply(id, request);

        ApiResponse<SupplyResponse> response = new ApiResponse<>(
                "SUCCESS",
                200,
                "Cập nhật vật tư thành công",
                supply
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupply(
            @PathVariable Long id
    ) {

        supplyService.deleteSupply(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SupplyResponse>>> getAllSupplies() {

        List<SupplyResponse> supplies =
                supplyService.getAllActiveSupplies();

        ApiResponse<List<SupplyResponse>> response =
                new ApiResponse<>(
                        "SUCCESS",
                        200,
                        "Lấy danh sách vật tư thành công",
                        supplies
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<SupplyResponse>>> searchSupplies(
            @RequestParam("name") String name
    ) {

        List<SupplyResponse> supplies =
                supplyService.searchSupplies(name);

        ApiResponse<List<SupplyResponse>> response =
                new ApiResponse<>(
                        "SUCCESS",
                        200,
                        "Tìm kiếm vật tư thành công",
                        supplies
                );

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/export")
    public ResponseEntity<ApiResponse<SupplyResponse>> exportSupply(
            @PathVariable Long id,
            @RequestBody StockAmountRequest request
    ) {

        SupplyResponse supply =
                supplyService.exportSupply(id, request);

        ApiResponse<SupplyResponse> response =
                new ApiResponse<>(
                        "SUCCESS",
                        200,
                        "Xuất kho thành công",
                        supply
                );

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/import")
    public ResponseEntity<ApiResponse<SupplyResponse>> importSupply(
            @PathVariable Long id,
            @RequestBody StockAmountRequest request
    ) {

        SupplyResponse supply =
                supplyService.importSupply(id, request);

        ApiResponse<SupplyResponse> response =
                new ApiResponse<>(
                        "SUCCESS",
                        200,
                        "Nhập kho thành công",
                        supply
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistics/daily-export")
    public ResponseEntity<ApiResponse<List<DailyExportResponse>>>
    getDailyExportStatistics() {

        List<DailyExportResponse> statistics =
                supplyService.getDailyExportStatistics();

        ApiResponse<List<DailyExportResponse>> response =
                new ApiResponse<>(
                        "SUCCESS",
                        200,
                        "Thống kê xuất kho trong ngày thành công",
                        statistics
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistics/top-export")
    public ResponseEntity<ApiResponse<TopExportResponse>>
    getTopExportSupply() {

        TopExportResponse result =
                supplyService.getTopExportSupply();

        ApiResponse<TopExportResponse> response =
                new ApiResponse<>(
                        "SUCCESS",
                        200,
                        "Thống kê vật tư xuất kho nhiều nhất thành công",
                        result
                );

        return ResponseEntity.ok(response);
    }
}