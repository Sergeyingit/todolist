package com.github.sergeyingit.todolist.dao;

import com.github.sergeyingit.todolist.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();
    public User findById(int id);
    public User findByEmail(String email);
    public void saveUser(User user);
    public void deleteUser(int id);
}
