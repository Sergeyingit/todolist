package com.github.sergeyingit.todolist.job;

import com.github.sergeyingit.todolist.service.EmailReminder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmailReminderJob {

    @Autowired
    private EmailReminder emailReminder;

    @Scheduled(cron = "${spring.job.cron}")
    public void sendEmailReminder() {
        emailReminder.sendReminder();
    }
}
