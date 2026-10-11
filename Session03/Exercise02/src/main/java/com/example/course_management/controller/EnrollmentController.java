package com.example.course_management.controller;

import com.example.course_management.model.Enrollment;
import com.example.course_management.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<List<Enrollment>> getAll() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getById(@PathVariable int id) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);

        if (enrollment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(enrollment);
    }

    @PostMapping
    public ResponseEntity<Enrollment> create(@RequestBody Enrollment enrollment) {
        if (enrollment.getId() <= 0 ||
                enrollment.getStudentName() == null ||
                enrollment.getStudentName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Enrollment created = enrollmentService.createEnrollment(enrollment);

        return ResponseEntity
                .created(URI.create("/enrollments/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> update(
            @PathVariable int id,
            @RequestBody Enrollment enrollment) {

        Enrollment updated = enrollmentService.updateEnrollment(id, enrollment);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Enrollment deleted = enrollmentService.deleteEnrollmentById(id);

        if (deleted == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}