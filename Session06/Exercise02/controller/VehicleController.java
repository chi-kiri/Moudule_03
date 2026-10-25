package com.example.parking_management.controller;

import com.example.parking_management.dto.VehicleCreateRequest;
import com.example.parking_management.dto.VehicleResponse;
import com.example.parking_management.response.ApiResponse;
import com.example.parking_management.response.PageResponse;
import com.example.parking_management.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @RequestBody VehicleCreateRequest req) {

        vehicleService.createVehicle(req);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                true,
                                "Tạo phương tiện thành công",
                                null
                        )
                );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<VehicleResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String keyword) {

        PageResponse<VehicleResponse> result =
                vehicleService.getPagedVehicles(
                        page,
                        size,
                        sortBy,
                        direction,
                        keyword
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lấy danh sách phương tiện thành công",
                        result
                )
        );
    }
}