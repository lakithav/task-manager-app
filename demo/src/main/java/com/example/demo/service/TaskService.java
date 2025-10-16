package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // This method validates if a task with the same title exists
    public Task createTask(Task task) {
        if (taskRepository.findByTitle(task.getTitle()).isPresent()) {
            throw new IllegalStateException("A task with this title already exists.");
        }
        return taskRepository.save(task);
    }
}
