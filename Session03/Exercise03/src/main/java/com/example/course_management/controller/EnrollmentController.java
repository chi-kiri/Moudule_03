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
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lấy danh sách đăng ký thành công",
                        enrollmentService.getAllEnrollments()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getById(@PathVariable int id) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);

        if (enrollment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Không tìm thấy đăng ký", null));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lấy đăng ký thành công", enrollment)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> create(
            @RequestBody Enrollment enrollment) {

        Enrollment created = enrollmentService.createEnrollment(enrollment);

        if (created == null) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Dữ liệu không hợp lệ", null));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Tạo đăng ký thành công", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> update(
            @PathVariable int id,
            @RequestBody Enrollment enrollment) {

        Enrollment updated = enrollmentService.updateEnrollment(id, enrollment);

        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Không tìm thấy đăng ký", null));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Cập nhật đăng ký thành công", updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> delete(@PathVariable int id) {
        Enrollment deleted = enrollmentService.deleteEnrollmentById(id);

        if (deleted == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Không tìm thấy đăng ký", null));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Xóa đăng ký thành công", deleted)
        );
    }
}