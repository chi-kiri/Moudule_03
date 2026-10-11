package com.example.course_management.service;

import com.example.course_management.model.Course;
import com.example.course_management.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course createCourse(Course course) {
        return courseRepository.create(course);
    }

    public Course updateCourse(long id, Course course) {
        return courseRepository.update(id, course);
    }

    public Course deleteCourseById(long id) {
        return courseRepository.deleteById(id);
    }
}