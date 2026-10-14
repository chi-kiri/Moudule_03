package com.example.course_management.service;

import com.example.course_management.model.Course;
import com.example.course_management.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course newCourse) {
        Course course = findCourseById(id);

        course.setTitle(newCourse.getTitle());
        course.setStatus(newCourse.getStatus());
        course.setInstructor(newCourse.getInstructor());

        return courseRepository.save(course);
    }

    public Course deleteCourse(Long id) {
        Course course = findCourseById(id);
        courseRepository.delete(course);
        return course;
    }
}