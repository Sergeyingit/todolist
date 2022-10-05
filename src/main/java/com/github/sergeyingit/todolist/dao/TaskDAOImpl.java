package com.github.sergeyingit.todolist.dao;

import com.github.sergeyingit.todolist.entity.Task;
import com.github.sergeyingit.todolist.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TaskDAOImpl implements TaskDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Task findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, id);
        return task;
    }

    @Override
    public void saveTask(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(task);
    }

    @Override
    public void deleteTask(int id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, id);
        User user = task.getTaskUser();
        user.getTasks().remove(task);

        session.delete(task);
    }
}
