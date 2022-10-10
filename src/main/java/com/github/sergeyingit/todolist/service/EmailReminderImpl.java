package com.github.sergeyingit.todolist.service;

import com.github.sergeyingit.todolist.entity.Task;
import com.github.sergeyingit.todolist.entity.User;
import com.github.sergeyingit.todolist.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailReminderImpl implements EmailReminder{

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


    @Override
    public void sendReminder() {
        List<User> users = userService.getUserWithTasksForToday();

        for (User user : users) {
            String email = user.getEmail();
            String subject = "ToDoList: Your tasks for today";
            List<Task> tasks = user.getTasks();
            StringBuilder stringBuilder = new StringBuilder();

            for (Task task : tasks) {
                String text = String.format("Title: %s, Description: %s, Date: %s \n",
                        task.getTitle(), task.getDescription(), task.getDate());
                stringBuilder.append(text);
            }

            String message = new String(stringBuilder);
            emailService.sendSimpleMailMessage(email, subject, message);

        }
    }

}
