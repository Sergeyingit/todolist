package com.github.sergeyingit.todolist.service;

import com.github.sergeyingit.todolist.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User findById(int id);
    public User findByEmail(String email);
    public void saveUser(User user) throws IOException;
    public void deleteUser(int id);
}
