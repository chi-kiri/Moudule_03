package com.example.course_management.controller;

import com.example.course_management.dto.StudentEnrollmentRequest;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.service.StudentEnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students-enrollments")
public class StudentEnrollmentController {

    private final StudentEnrollmentService studentEnrollmentService;

    public StudentEnrollmentController(
            StudentEnrollmentService studentEnrollmentService) {
        this.studentEnrollmentService = studentEnrollmentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> enroll(
            @RequestBody StudentEnrollmentRequest req) {

        try {
            studentEnrollmentService.enrollStudent(
                    req.getStudentId(),
                    req.getCourseId()
            );

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(
                            new ApiResponse<>(
                                    true,
                                    "Thêm sinh viên vào khóa học thành công",
                                    null
                            )
                    );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            new ApiResponse<>(
                                    false,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }
}