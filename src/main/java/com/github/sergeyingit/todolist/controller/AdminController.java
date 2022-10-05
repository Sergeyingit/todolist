package com.github.sergeyingit.todolist.controller;

import com.github.sergeyingit.todolist.entity.User;
import com.github.sergeyingit.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;


//    @GetMapping("")
//    public String getAdminPage() {
//        return "admin";
//    }

    @GetMapping("")
    public String getAdminPageUsers(Model model) {

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/users/tasks")
    public String getAdminPageUserTask(@RequestParam("userId") int userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "admin-users-task";
    }

    @GetMapping("/users/delete")
    public String deleteUser(@RequestParam("userId") int userId) {
        userService.deleteUser(userId);
        return "redirect:/admin";
    }

}
