package com.example.course_management.service;

import com.example.course_management.dto.InstructorCreateRequest;
import com.example.course_management.model.Instructor;
import com.example.course_management.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor findInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
    }

    public Instructor createInstructor(InstructorCreateRequest req) {
        Instructor instructor = new Instructor();

        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());

        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructor(Long id, Instructor newInstructor) {
        Instructor instructor = findInstructorById(id);

        instructor.setName(newInstructor.getName());
        instructor.setEmail(newInstructor.getEmail());

        return instructorRepository.save(instructor);
    }

    public void deleteInstructor(Long id) {
        Instructor instructor = findInstructorById(id);
        instructorRepository.delete(instructor);
    }
}