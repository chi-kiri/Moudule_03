package com.example.course_management.repository;

import com.example.course_management.model.Instructor;
import com.example.course_management.model.CourseStatus;
import com.example.course_management.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>(List.of(
            new Course(
                    1L,
                    "Java Web",
                    CourseStatus.ACTIVE,
                    new Instructor(1L, "Nguyen Van A", "a@gmail.com")
            ),
            new Course(
                    2L,
                    "Spring Boot",
                    CourseStatus.ACTIVE,
                    new Instructor(2L, "Tran Van B", "b@gmail.com")
            )
    ));

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(Long id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    public Course create(Course course) {
        courses.add(course);
        return course;
    }

    public Course update(Long id, Course newCourse) {
        Course course = findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(newCourse.getTitle());
        course.setStatus(newCourse.getStatus());
        course.setInstructor(newCourse.getInstructor());

        return course;
    }

    public Course deleteById(Long id) {
        Course course = findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        courses.remove(course);
        return course;
    }
}