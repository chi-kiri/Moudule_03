package com.example.course_management.controller;

import com.example.course_management.model.Course;
import com.example.course_management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<List<Course>> getAll() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable int id) {
        Course course = courseService.getCourseById(id);

        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(course);
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        if (course.getId() <= 0 ||
                course.getTitle() == null ||
                course.getTitle().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Course created = courseService.createCourse(course);

        return ResponseEntity
                .created(URI.create("/courses/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(
            @PathVariable int id,
            @RequestBody Course course) {

        Course updated = courseService.updateCourse(id, course);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Course deleted = courseService.deleteCourseById(id);

        if (deleted == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}