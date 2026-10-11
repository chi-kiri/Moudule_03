package com.example.course_management.repository;

import com.example.course_management.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>(List.of(
            new Course(1, "Java Web", "active", 1),
            new Course(2, "Spring Boot", "active", 2)
    ));

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(long id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }

    public Course create(Course course) {
        courses.add(course);
        return course;
    }

    public Course update(long id, Course newCourse) {
        Course course = findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(newCourse.getTitle());
        course.setStatus(newCourse.getStatus());
        course.setInstructorId(newCourse.getInstructorId());

        return course;
    }

    public Course deleteById(long id) {
        Course course = findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        courses.remove(course);
        return course;
    }
}