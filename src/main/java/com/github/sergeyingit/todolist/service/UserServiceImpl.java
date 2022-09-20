package com.github.sergeyingit.todolist.service;

import com.github.sergeyingit.todolist.dao.UserDAO;
//import com.github.sergeyingit.todolist.entity.Role;
//import com.github.sergeyingit.todolist.entity.Role;
import com.github.sergeyingit.todolist.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation {@link UserService}
 */
@Service
public class UserServiceImpl implements UserService{


    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    @Transactional
    public void saveUser(User user) throws IOException {
        if (findByEmail(user.getEmail()) != null) {
            throw new IOException("There is an account with that email address: " + user.getEmail());
        }


        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }
}
