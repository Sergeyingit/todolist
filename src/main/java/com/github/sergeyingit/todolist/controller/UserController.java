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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{userId}")
    public String getUserPage(@PathVariable("userId") int userId, Model model, Authentication authentication) {
        String name = authentication.getName();
        User currentUser = userService.findByEmail(name);


        if (currentUser != null && currentUser.getId() == userId) {
            model.addAttribute("userId", userId);
            model.addAttribute("name", name);
            return "user";
        } else if (currentUser != null) {
            return "redirect:/user/" + currentUser.getId();
        }

        return "redirect:/error404";
    }


}
