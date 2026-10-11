package com.example.course_management.repository;

import com.example.course_management.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnrollmentRepository {

    private final List<Enrollment> enrollments = List.of(
            new Enrollment(1, "Student A", 1),
            new Enrollment(2, "Student B", 2)
    );

    public List<Enrollment> findAll() {
        return enrollments;
    }
}