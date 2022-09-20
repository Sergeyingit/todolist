package com.github.sergeyingit.todolist.service;

import com.github.sergeyingit.todolist.dao.UserDAO;
import com.github.sergeyingit.todolist.entity.User;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private List<User> users;

    private UserService userService;

    private UserDAO userDAO;

    @BeforeEach
    public void init() {
        userDAO = Mockito.mock(UserDAO.class);
        userService = new UserServiceImpl(userDAO);
        users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("demo@demo.com");
        User user2 = new User();
        user2.setId(2);
        user2.setEmail("demo2@demo.com");
        users.add(user1);
        users.add(user2);

    }

    @Test
    public void findByIdTest() {

        User user1 = users.get(0);

        Mockito.when(userDAO.findById(user1.getId())).thenReturn(users.get(0));
        User found = userService.findById(user1.getId());
        assertEquals(user1, found);
        Mockito.verify(userDAO, Mockito.times(1)).findById(user1.getId());
    }

    @Test
    public void getAllUsersTest() {

        Mockito.when(userDAO.getAllUsers()).thenReturn(users);
        List<User> userList = userService.getAllUsers();
        assertEquals(2, userList.size());
        Mockito.verify(userDAO, Mockito.times(1)).getAllUsers();
    }

    @Test
    public void findByEmailTest() {
        User user2 = users.get(1);
        Mockito.when(userDAO.findByEmail(user2.getEmail())).thenReturn(users.get(1));

        User found = userService.findByEmail(user2.getEmail());
        assertEquals(user2, found);
        Mockito.verify(userDAO, Mockito.times(1)).findByEmail(user2.getEmail());
    }

    @Test
    public void saveUserTest() throws IOException {

        for(User user: users) {
            Mockito.when(userDAO.findByEmail(user.getEmail())).thenReturn(user);

            Throwable exception = assertThrows(IOException.class,
                    () -> userService.saveUser(user));
            assertEquals("There is an account with that email address: " + user.getEmail(), exception.getMessage());
        }

        User user3 = new User();
        user3.setId(2);
        user3.setEmail("demo3@demo.com");
        Mockito.when(userDAO.findByEmail(user3.getEmail())).thenReturn(null);
        userService.saveUser(user3);
        Mockito.verify(userDAO, Mockito.times(1)).saveUser(user3);

    }

    @Test
    public void deleteUserTest() {
        User user2 = users.get(1);

        userService.deleteUser(user2.getId());

        Mockito.verify(userDAO, Mockito.times(1)).deleteUser(user2.getId());
    }
}