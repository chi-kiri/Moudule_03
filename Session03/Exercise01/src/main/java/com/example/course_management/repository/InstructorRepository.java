package com.example.course_management.repository;

import com.example.course_management.model.Instructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstructorRepository {

    private final List<Instructor> instructors = List.of(
            new Instructor(1, "Nguyen Van A", "a@gmail.com"),
            new Instructor(2, "Tran Van B", "b@gmail.com")
    );

    public List<Instructor> findAll() {
        return instructors;
    }
}