package com.example.employeesecurity.controller;

import com.example.employeesecurity.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @GetMapping
    public List<Employee> getEmployees() {
        return List.of(
                new Employee(1L, "Nguyen Van A", 15000000),
                new Employee(2L, "Tran Thi B", 18000000),
                new Employee(3L, "Le Van C", 20000000)
        );
    }
}