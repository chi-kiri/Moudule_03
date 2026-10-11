package com.example.course_management.controller;

import com.example.course_management.model.Instructor;
import com.example.course_management.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> getAll() {
        return ResponseEntity.ok(instructorService.getAllInstructors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getById(@PathVariable int id) {
        Instructor instructor = instructorService.getInstructorById(id);

        if (instructor == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(instructor);
    }

    @PostMapping
    public ResponseEntity<Instructor> create(@RequestBody Instructor instructor) {
        if (instructor.getId() <= 0 ||
                instructor.getName() == null ||
                instructor.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Instructor created = instructorService.createInstructor(instructor);

        return ResponseEntity
                .created(URI.create("/instructors/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> update(
            @PathVariable int id,
            @RequestBody Instructor instructor) {

        Instructor updated = instructorService.updateInstructor(id, instructor);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Instructor deleted = instructorService.deleteInstructorById(id);

        if (deleted == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}