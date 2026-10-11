package com.example.course_management.repository;

import com.example.course_management.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>(List.of(
            new Enrollment(1, "Student A", 1),
            new Enrollment(2, "Student B", 2)
    ));

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public Enrollment findById(int id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }
        return null;
    }

    public Enrollment create(Enrollment enrollment) {
        enrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment update(int id, Enrollment newEnrollment) {
        Enrollment enrollment = findById(id);

        if (enrollment == null) {
            return null;
        }

        enrollment.setStudentName(newEnrollment.getStudentName());
        enrollment.setCourseId(newEnrollment.getCourseId());

        return enrollment;
    }

    public Enrollment deleteById(int id) {
        Enrollment enrollment = findById(id);

        if (enrollment != null) {
            enrollments.remove(enrollment);
        }

        return enrollment;
    }
}