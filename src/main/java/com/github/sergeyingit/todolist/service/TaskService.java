package com.github.sergeyingit.todolist.service;

import com.github.sergeyingit.todolist.entity.Task;
import com.github.sergeyingit.todolist.entity.User;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * {@link Service} for handling {@link Task} entity.
 */
public interface TaskService {
    /**
     * Get {@link Task} by id.
     * @param id int.
     * @return {@link Task} with id or null.
     */
    Task findById(int id);

    /**
     * Save {@link Task} entity.
     * @param task Task object.
     */
    void saveTask(Task task);

    /**
     * Delete {@link Task} by id.
     * @param id int.
     */
    void deleteTask(int id);
}
