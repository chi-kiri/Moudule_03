package com.example.course_management.controller;

import com.example.course_management.dto.CourseCreateRequest;
import com.example.course_management.dto.CourseUpdateRequest;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @RequestBody CourseCreateRequest req) {

        try {
            courseService.createCourse(req);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(
                            new ApiResponse<>(
                                    true,
                                    "Tạo khóa học thành công",
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

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long id,
            @RequestBody CourseUpdateRequest req) {

        try {
            courseService.updateCourse(id, req);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Cập nhật khóa học thành công",
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