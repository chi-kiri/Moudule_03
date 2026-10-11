package com.example.course_management.model;

public class Course {
    private int id;
    private String title;
    private String status;
    private int instructorId;

    public Course(int id, String title, String status, int instructorId) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.instructorId = instructorId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public int getInstructorId() {
        return instructorId;
    }
}