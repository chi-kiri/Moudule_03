package com.example.course_management.service;

import com.example.course_management.dto.CourseCreateRequest;
import com.example.course_management.dto.CourseUpdateRequest;
import com.example.course_management.model.Course;
import com.example.course_management.model.Instructor;
import com.example.course_management.repository.CourseRepository;
import com.example.course_management.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public CourseService(
            CourseRepository courseRepository,
            InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course createCourse(CourseCreateRequest req) {
        Instructor instructor = instructorRepository.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        Course course = new Course();

        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, CourseUpdateRequest req) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Instructor instructor = instructorRepository.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        return courseRepository.save(course);
    }

    public Course deleteCourse(Long id) {
        Course course = findCourseById(id);

        courseRepository.delete(course);

        return course;
    }
}