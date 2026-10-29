package com.example.hrm_management.service;

import com.example.hrm_management.dto.DepartmentDTO;
import com.example.hrm_management.model.Department;
import com.example.hrm_management.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(DepartmentDTO dto) {
        Department department = new Department();

        department.setName(dto.getName());
        department.setDescription(dto.getDescription());

        return departmentRepository.save(department);
    }
}