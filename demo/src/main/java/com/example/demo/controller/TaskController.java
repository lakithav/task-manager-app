package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tasks")
// This allows requests from your React app, but doesn't check who the user is.
@CrossOrigin(origins = "http://localhost:3000") 
public class TaskController {

    @Autowired
    private TaskService taskService;

    // This endpoint is completely public. Anyone can see all the tasks.
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // This endpoint is also public. Anyone can create a new task.
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
}

