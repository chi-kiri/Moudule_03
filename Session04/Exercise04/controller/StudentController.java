package com.example.course_management.controller;

import com.example.course_management.dto.StudentCreateRequest;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @RequestBody StudentCreateRequest req) {

        studentService.createStudent(req);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                true,
                                "Tạo sinh viên thành công",
                                null
                        )
                );
    }
}