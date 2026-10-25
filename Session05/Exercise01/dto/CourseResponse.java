package com.example.course_management.dto;

import com.example.course_management.model.CourseStatus;

public class CourseResponse {

    private Long id;
    private String title;
    private CourseStatus status;
    private Long instructorId;

    public CourseResponse() {
    }

    public CourseResponse(
            Long id,
            String title,
            CourseStatus status,
            Long instructorId) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.instructorId = instructorId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public Long getInstructorId() {
        return instructorId;
    }
}