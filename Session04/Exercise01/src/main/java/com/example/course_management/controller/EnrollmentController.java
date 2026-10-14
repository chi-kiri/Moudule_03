package com.example.course_management.controller;

import com.example.course_management.model.Enrollment;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAll() {
        List<Enrollment> enrollments =
                enrollmentService.getAllEnrollments();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lấy danh sách đăng ký thành công",
                        enrollments
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getById(
            @PathVariable long id) {

        try {
            Enrollment enrollment =
                    enrollmentService.getEnrollmentById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Lấy đăng ký thành công",
                            enrollment
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            new ApiResponse<>(
                                    false,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> create(
            @RequestBody Enrollment enrollment) {

        Enrollment created =
                enrollmentService.createEnrollment(enrollment);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                true,
                                "Tạo đăng ký thành công",
                                created
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> update(
            @PathVariable long id,
            @RequestBody Enrollment enrollment) {

        try {
            Enrollment updated =
                    enrollmentService.updateEnrollment(id, enrollment);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Cập nhật đăng ký thành công",
                            updated
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            new ApiResponse<>(
                                    false,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> delete(
            @PathVariable long id) {

        try {
            Enrollment deleted =
                    enrollmentService.deleteEnrollmentById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Xóa đăng ký thành công",
                            deleted
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
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