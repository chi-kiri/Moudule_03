package com.example.hrm_management.controller;

import com.example.hrm_management.dto.DepartmentDTO;
import com.example.hrm_management.model.Department;
import com.example.hrm_management.response.ApiResponse;
import com.example.hrm_management.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Department>> createDepartment(
            @Valid @RequestBody DepartmentDTO dto) {

        Department department = departmentService.createDepartment(dto);

        ApiResponse<Department> response =
                new ApiResponse<>(
                        "SUCCESS",
                        "Tạo phòng ban thành công",
                        department
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}