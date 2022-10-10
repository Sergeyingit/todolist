package com.github.sergeyingit.todolist.dao;

import com.github.sergeyingit.todolist.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    User findById(int id);
    User findByEmail(String email);
    void saveUser(User user);
    void deleteUser(int id);
    List<User> getUserWithTasksForToday();


}
