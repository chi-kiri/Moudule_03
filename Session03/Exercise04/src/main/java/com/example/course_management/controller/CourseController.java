package com.example.course_management.controller;

import com.example.course_management.model.Course;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAll() {
        List<Course> courses = courseService.getAllCourses();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lấy danh sách khóa học thành công",
                        courses
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getById(
            @PathVariable long id) {

        try {
            Course course = courseService.getCourseById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Lấy khóa học thành công",
                            course
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
    public ResponseEntity<ApiResponse<Course>> create(
            @RequestBody Course course) {

        Course created = courseService.createCourse(course);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                true,
                                "Tạo khóa học thành công",
                                created
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> update(
            @PathVariable long id,
            @RequestBody Course course) {

        try {
            Course updated = courseService.updateCourse(id, course);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Cập nhật khóa học thành công",
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
    public ResponseEntity<ApiResponse<Course>> delete(
            @PathVariable long id) {

        try {
            Course deleted = courseService.deleteCourseById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Xóa khóa học thành công",
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