package com.github.sergeyingit.todolist.controller;

import com.github.sergeyingit.todolist.entity.User;
import com.github.sergeyingit.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }



    @GetMapping("/error{status}")
    public String getAdminPage(@PathVariable("status") String status, Model model) {
        model.addAttribute("status", status);
        return "error";
    }

    @GetMapping("registration")
    public String getRegistrationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "registration";
    }

    @PostMapping("registration")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            ObjectError errorPasswordMatches = errorList.stream().filter((objectError
                    -> "PasswordMatches".equals(objectError.getCode())))
                    .findFirst().orElse(null);
            if (errorPasswordMatches != null) {
                model.addAttribute("errorPasswordMatches", errorPasswordMatches.getDefaultMessage());
            }
            return "registration";
        }
        try {
            userService.saveUser(user);
        } catch (IOException e) {
            model.addAttribute("messageError", e.getMessage());
            return "registration";
        }

        return "redirect:/";
    }
}
