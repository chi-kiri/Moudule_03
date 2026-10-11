package com.example.course_management.model;

public class Enrollment {
    private int id;
    private String studentName;
    private int courseId;

    public Enrollment(int id, String studentName, int courseId) {
        this.id = id;
        this.studentName = studentName;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getCourseId() {
        return courseId;
    }
}