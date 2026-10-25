package com.example.course_management.controller;

import com.example.course_management.dto.CourseCreateRequest;
import com.example.course_management.dto.CourseResponse;
import com.example.course_management.dto.CourseUpdateRequest;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.course_management.model.CourseStatus;
import com.example.course_management.response.PageResponse;
import com.example.course_management.dto.CourseResponseV2;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponseV2>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(defaultValue = "ACTIVE") CourseStatus status) {

        PageResponse<CourseResponseV2> result =
                courseService.getPagedCoursesV2(
                        page,
                        size,
                        sortBy,
                        direction,
                        status
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lấy danh sách khóa học thành công",
                        result
                )
        );
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