package com.example.course_management.service;

import com.example.course_management.model.Instructor;
import com.example.course_management.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructorById(long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
    }

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.create(instructor);
    }

    public Instructor updateInstructor(long id, Instructor instructor) {
        return instructorRepository.update(id, instructor);
    }

    public Instructor deleteInstructorById(long id) {
        return instructorRepository.deleteById(id);
    }
}