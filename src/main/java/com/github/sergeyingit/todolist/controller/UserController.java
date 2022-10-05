package com.github.sergeyingit.todolist.controller;

import com.github.sergeyingit.todolist.entity.Task;
import com.github.sergeyingit.todolist.entity.User;
import com.github.sergeyingit.todolist.service.TaskService;
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

    @Autowired
    private TaskService taskService;


    @GetMapping("/{userId}/tasks")
    public String getUserPage(@PathVariable("userId") int userId, Model model, Authentication authentication) {
        String name = authentication.getName();
        User currentUser = userService.findByEmail(name);
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a-> a.getAuthority().equals("ROLE_ADMIN"));

        User requestedUser = userService.findById(userId);


        if (currentUser != null && currentUser.equals(requestedUser) || isAdmin && requestedUser != null) {
            model.addAttribute("user", requestedUser);
            return "user";
        } else if (currentUser != null && requestedUser != null) {
            return "redirect:/error403";
        }

        return "redirect:/error404";
    }

    @GetMapping("/{userId}/tasks/create")
    public String getCreateTaskPage(@PathVariable("userId") int userId, Model model) {

        Task task = new Task();
        task.setUserId(userId);
        model.addAttribute("task", task);
        return "user-task-info";
    }


    @PostMapping("/{userId}/tasks/saveTask")
    public String saveTask(@PathVariable("userId") int userId, @Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user-task-info";
        }
//        if (task.getDate().equals("")) {
//            task.setDate(null);
//        }
        taskService.saveTask(task);
        return "redirect:/user/" + userId + "/tasks";
    }

    @GetMapping("/{userId}/tasks/delete")
    public String deleteTask(@PathVariable("userId") int userId, @RequestParam("taskId") int taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/user/" + userId  + "/tasks";
    }

    @GetMapping("/{userId}/tasks/update")
    public String updateTask(@PathVariable("userId") int userId, @RequestParam("taskId") int taskId,Model model) {
        Task task = taskService.findById(taskId);
        model.addAttribute("task", task);
        return "user-task-info";
    }


}
