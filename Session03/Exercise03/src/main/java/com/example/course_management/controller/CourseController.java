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
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lấy danh sách khóa học thành công",
                        courseService.getAllCourses()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getById(@PathVariable int id) {
        Course course = courseService.getCourseById(id);

        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Không tìm thấy khóa học", null));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lấy khóa học thành công", course)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> create(@RequestBody Course course) {
        Course created = courseService.createCourse(course);

        if (created == null) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Dữ liệu không hợp lệ", null));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Tạo khóa học thành công", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> update(
            @PathVariable int id,
            @RequestBody Course course) {

        Course updated = courseService.updateCourse(id, course);

        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Không tìm thấy khóa học", null));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Cập nhật khóa học thành công", updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> delete(@PathVariable int id) {
        Course deleted = courseService.deleteCourseById(id);

        if (deleted == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Không tìm thấy khóa học", null));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Xóa khóa học thành công", deleted)
        );
    }
}