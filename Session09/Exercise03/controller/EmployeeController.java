package com.example.hrm_management.controller;

import com.example.hrm_management.dto.EmployeeCreateDTO;
import com.example.hrm_management.model.Employee;
import com.example.hrm_management.response.ApiResponse;
import com.example.hrm_management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> createEmployee(
            @Valid @RequestBody EmployeeCreateDTO dto) {

        Employee employee = employeeService.createEmployee(dto);

        ApiResponse<Employee> response =
                new ApiResponse<>(
                        "SUCCESS",
                        "Thêm nhân viên thành công",
                        employee
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}