package com.example.task_management.model;

public class Task {
    private int id;
    private String title;
    private String description;
    private String priority;
    private int assignedTo;

    public Task(int id, String title, String description, String priority, int assignedTo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.assignedTo = assignedTo;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public int getAssignedTo() {
        return assignedTo;
    }
}