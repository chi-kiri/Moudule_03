package com.example.task_management.repository;

import com.example.task_management.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {

    private final List<Task> tasks = List.of(
            new Task(1, "Task 1", "Description 1", "high", 1),
            new Task(2, "Task 2", "Description 2", "medium", 2),
            new Task(3, "Task 3", "Description 3", "low", 3),
            new Task(4, "Task 4", "Description 4", "high", 1),
            new Task(5, "Task 5", "Description 5", "medium", 2),
            new Task(6, "Task 6", "Description 6", "low", 3),
            new Task(7, "Task 7", "Description 7", "high", 1),
            new Task(8, "Task 8", "Description 8", "medium", 2),
            new Task(9, "Task 9", "Description 9", "low", 3),
            new Task(10, "Task 10", "Description 10", "high", 1)
    );

    public List<Task> findAll() {
        return tasks;
    }
}