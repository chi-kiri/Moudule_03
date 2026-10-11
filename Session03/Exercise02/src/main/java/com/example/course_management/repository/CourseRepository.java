package com.example.course_management.repository;

import com.example.course_management.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>(List.of(
            new Course(1, "Java Web", "active", 1),
            new Course(2, "Spring Boot", "active", 2)
    ));

    public List<Course> findAll() {
        return courses;
    }

    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    public Course create(Course course) {
        courses.add(course);
        return course;
    }

    public Course update(int id, Course newCourse) {
        Course course = findById(id);

        if (course == null) {
            return null;
        }

        course.setTitle(newCourse.getTitle());
        course.setStatus(newCourse.getStatus());
        course.setInstructorId(newCourse.getInstructorId());

        return course;
    }

    public Course deleteById(int id) {
        Course course = findById(id);

        if (course != null) {
            courses.remove(course);
        }

        return course;
    }
}