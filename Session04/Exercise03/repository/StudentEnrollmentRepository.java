package com.example.course_management.repository;

import com.example.course_management.model.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {
}