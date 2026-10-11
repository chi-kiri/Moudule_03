package com.example.course_management.repository;

import com.example.course_management.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {

    private final List<Course> courses = List.of(
            new Course(1, "Java Web", "active", 1),
            new Course(2, "Spring Boot", "active", 2)
    );

    public List<Course> findAll() {
        return courses;
    }
}