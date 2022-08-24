package com.github.sergeyingit.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyController {

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping("/user/{userId}")
    public String getUserPage(@PathVariable("userId") int userId, Model model) {
        model.addAttribute("userId", userId);
        return "user";
    }

    @GetMapping("/error{status}")
    public String getAdminPage(@PathVariable("status") String status, Model model) {
        model.addAttribute("status", status);
        return "error";
    }
}
