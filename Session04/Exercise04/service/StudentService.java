package com.example.course_management.service;

import com.example.course_management.dto.StudentCreateRequest;
import com.example.course_management.model.Student;
import com.example.course_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(StudentCreateRequest req) {
        Student student = new Student();

        student.setName(req.getName());
        student.setEmail(req.getEmail());

        return studentRepository.save(student);
    }
}