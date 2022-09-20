package com.github.sergeyingit.todolist.service;

import com.github.sergeyingit.todolist.entity.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * {@link Service} for handling {@link User} entity.
 */
public interface UserService {
    /**
     * Get all {@link User} entity.
     * @return collection of {@link User} entity.
     */
    List<User> getAllUsers();

    /**
     * Get {@link User} by id.
     * @param id int.
     * @return {@link User} with id or null.
     */
    User findById(int id);

    /**
     * Get {@link User} by email.
     * @param email String.
     * @return {@link User} with email or null.
     */
    User findByEmail(String email);

    /**
     * Save {@link User} entity.
     * @param user User object.
     * @throws IOException throw IOException if email exists.
     */
    void saveUser(User user) throws IOException;

    /**
     * Delete {@link User} by id.
     * @param id int.
     */
    void deleteUser(int id);
}
