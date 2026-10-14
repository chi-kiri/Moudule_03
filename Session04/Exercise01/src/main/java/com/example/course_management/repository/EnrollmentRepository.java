package com.example.course_management.repository;

import com.example.course_management.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>(List.of(
            new Enrollment(1, "Student A", 1),
            new Enrollment(2, "Student B", 2)
    ));

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public Optional<Enrollment> findById(long id) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId() == id)
                .findFirst();
    }

    public Enrollment create(Enrollment enrollment) {
        enrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment update(long id, Enrollment newEnrollment) {
        Enrollment enrollment = findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setStudentName(newEnrollment.getStudentName());
        enrollment.setCourseId(newEnrollment.getCourseId());

        return enrollment;
    }

    public Enrollment deleteById(long id) {
        Enrollment enrollment = findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollments.remove(enrollment);
        return enrollment;
    }
}