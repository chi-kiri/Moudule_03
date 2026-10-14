package com.example.course_management.service;

import com.example.course_management.model.Course;
import com.example.course_management.model.Student;
import com.example.course_management.model.StudentEnrollment;
import com.example.course_management.repository.CourseRepository;
import com.example.course_management.repository.StudentEnrollmentRepository;
import com.example.course_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentEnrollmentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentEnrollmentRepository studentEnrollmentRepository;

    public StudentEnrollmentService(
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            StudentEnrollmentRepository studentEnrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentEnrollmentRepository = studentEnrollmentRepository;
    }

    public StudentEnrollment enrollStudent(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        StudentEnrollment enrollment = new StudentEnrollment();

        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return studentEnrollmentRepository.save(enrollment);
    }
}