package com.github.sergeyingit.todolist.service;

import com.github.sergeyingit.todolist.dao.TaskDAO;
import com.github.sergeyingit.todolist.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private List<Task> tasks;
    private TaskService taskService;
    private TaskDAO taskDAO;

    @BeforeEach
    public void init() {
        taskDAO = Mockito.mock(TaskDAO.class);
        taskService = new TaskServiceImpl(taskDAO);
        tasks = new ArrayList<>();

        Task task1 = new Task();
        task1.setId(1);
        Task task2 = new Task();
        task1.setId(2);

        tasks.add(task1);
        tasks.add(task2);

    }

    @Test
    public void findByIdTest() {


        Task task1 = tasks.get(0);

        Mockito.when(taskDAO.findById(task1.getId())).thenReturn(tasks.get(0));
        Task found = taskService.findById(task1.getId());
        assertEquals(task1, found);
        Mockito.verify(taskDAO, Mockito.times(1)).findById(task1.getId());
    }

    @Test
    public void saveTaskTest() {

        Task task3 = new Task();
        task3.setId(3);

        taskService.saveTask(task3);
        Mockito.verify(taskDAO, Mockito.times(1)).saveTask(task3);

    }

    @Test
    public void deleteTaskTest() {
        Task task2 = tasks.get(1);

        taskService.deleteTask(task2.getId());

        Mockito.verify(taskDAO, Mockito.times(1)).deleteTask(task2.getId());

    }
}