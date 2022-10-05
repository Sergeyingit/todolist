package com.github.sergeyingit.todolist.dao;

import com.github.sergeyingit.todolist.entity.Task;
import com.github.sergeyingit.todolist.entity.User;

public interface TaskDAO {
    Task findById(int id);
    void saveTask(Task task);
    void deleteTask(int id);
}
