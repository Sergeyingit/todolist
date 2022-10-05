package com.github.sergeyingit.todolist.service;

import com.github.sergeyingit.todolist.dao.TaskDAO;
import com.github.sergeyingit.todolist.entity.Task;
import com.github.sergeyingit.todolist.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskDAO taskDAO;

    @Autowired
    public TaskServiceImpl(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    @Transactional
    public Task findById(int id) {
        return taskDAO.findById(id);
    }

    @Override
    @Transactional
    public void saveTask(Task task) {
        taskDAO.saveTask(task);
    }

    @Override
    @Transactional
    public void deleteTask(int id) {
        taskDAO.deleteTask(id);
    }
}
