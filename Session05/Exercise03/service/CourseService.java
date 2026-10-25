package com.example.course_management.service;

import com.example.course_management.dto.CourseCreateRequest;
import com.example.course_management.dto.CourseResponse;
import com.example.course_management.dto.CourseUpdateRequest;
import com.example.course_management.model.Course;
import com.example.course_management.model.Instructor;
import com.example.course_management.repository.CourseRepository;
import com.example.course_management.repository.InstructorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.course_management.model.CourseStatus;
import com.example.course_management.response.PageResponse;

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

    public Page<CourseResponse> getPagedCourses(
            int page,
            int size,
            String sortBy,
            Sort.Direction direction) {

        if (page < 0) {
            page = 0;
        }

        if (size <= 0) {
            size = 10;
        }

        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "id";
        }

        if (direction == null) {
            direction = Sort.Direction.DESC;
        }

        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(
                page,
                size,
                sort
        );

        Page<Course> courses = courseRepository.findAll(pageable);

        return courses.map(this::toResponse);
    }

    public Course findCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));
    }

    public Course createCourse(CourseCreateRequest req) {

        Instructor instructor = instructorRepository
                .findById(req.getInstructorId())
                .orElseThrow(() ->
                        new RuntimeException("Instructor not found"));

        Course course = new Course();

        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        return courseRepository.save(course);
    }

    public Course updateCourse(
            Long id,
            CourseUpdateRequest req) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        Instructor instructor = instructorRepository
                .findById(req.getInstructorId())
                .orElseThrow(() ->
                        new RuntimeException("Instructor not found"));

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

    private CourseResponse toResponse(Course course) {

        Long instructorId = null;

        if (course.getInstructor() != null) {
            instructorId = course.getInstructor().getId();
        }

        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getStatus(),
                instructorId
        );
    }

    public PageResponse<CourseResponse> getPagedCoursesByStatus(
            int page,
            int size,
            String sortBy,
            Sort.Direction direction,
            CourseStatus status) {

        if (page < 0) {
            page = 0;
        }

        if (size <= 0) {
            size = 10;
        }

        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "id";
        }

        if (direction == null) {
            direction = Sort.Direction.DESC;
        }

        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(
                page,
                size,
                sort
        );

        Page<Course> coursePage =
                courseRepository.findAllByStatus(
                        status,
                        pageable
                );

        Page<CourseResponse> responsePage =
                coursePage.map(this::toResponse);

        return new PageResponse<>(
                responsePage.getContent(),
                responsePage.getNumber(),
                responsePage.getSize(),
                (int) responsePage.getTotalElements(),
                responsePage.getTotalPages(),
                responsePage.isLast()
        );
    }
}