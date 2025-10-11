package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    // Test for successfully adding a valid task
    @Test
    public void whenCreateTaskWithValidTitle_thenSucceed() {
        // Arrange
        Task validTask = new Task("A valid title", "A description", false);
        when(taskRepository.save(any(Task.class))).thenReturn(validTask);

        // Act
        Task result = taskService.createTask(validTask);

        // Assert
        assertNotNull(result);
        assertEquals("A valid title", result.getTitle());

        // Verify
        verify(taskRepository, times(1)).save(validTask);
    }

    // Test for the validation logic (updated for the refactor)
    @Test
    public void whenCreateTaskWithEmptyTitle_thenThrowException() {
        // Arrange & Act & Assert: The exception is now thrown when the Task is constructed.
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("", "A description", false); // The error now happens here
        });

        // Verify: Ensure the save method is never called if construction fails
        verify(taskRepository, never()).save(any(Task.class));
    }
}
