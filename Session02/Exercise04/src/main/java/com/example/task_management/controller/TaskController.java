package com.example.task_management.controller;

import com.example.task_management.model.Task;
import com.example.task_management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAllTasks(
            @RequestParam(required = false) String search) {

        List<Task> tasks = taskService.findAllTasks();

        if (search != null && !search.isBlank()) {
            tasks = tasks.stream()
                    .filter(task -> task.getTitle()
                            .toLowerCase()
                            .contains(search.toLowerCase()))
                    .toList();
        }

        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task newTask) {

        if (!taskService.addTask(newTask)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }
}