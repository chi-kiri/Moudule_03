package com.example.parking_management.service;

import com.example.parking_management.dto.VehicleCreateRequest;
import com.example.parking_management.dto.VehicleResponse;
import com.example.parking_management.model.Vehicle;
import com.example.parking_management.repository.VehicleRepository;
import com.example.parking_management.response.PageResponse;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public void createVehicle(VehicleCreateRequest req) {
        Vehicle vehicle = new Vehicle();

        vehicle.setLicensePlate(req.getLicensePlate());
        vehicle.setColor(req.getColor());
        vehicle.setType(req.getVehicleType());

        vehicleRepository.save(vehicle);
    }

    public PageResponse<VehicleResponse> getPagedVehicles(
            int page,
            int size,
            String sortBy,
            String direction,
            String keyword) {

        if (page < 0) {
            page = 0;
        }

        Sort sort = Sort.unsorted();

        if (sortBy != null && !sortBy.isBlank()
                && direction != null && !direction.isBlank()) {

            Sort.Direction sortDirection =
                    direction.equalsIgnoreCase("DESC")
                            ? Sort.Direction.DESC
                            : Sort.Direction.ASC;

            sort = Sort.by(sortDirection, sortBy);
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<VehicleResponse> result =
                vehicleRepository.findAllByKeyword(keyword, pageable);

        return new PageResponse<>(
                result.getContent(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.isLast()
        );
    }
}