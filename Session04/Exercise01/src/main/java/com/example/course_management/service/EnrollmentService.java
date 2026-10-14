package com.example.course_management.service;

import com.example.course_management.model.Enrollment;
import com.example.course_management.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.create(enrollment);
    }

    public Enrollment updateEnrollment(long id, Enrollment enrollment) {
        return enrollmentRepository.update(id, enrollment);
    }

    public Enrollment deleteEnrollmentById(long id) {
        return enrollmentRepository.deleteById(id);
    }
}