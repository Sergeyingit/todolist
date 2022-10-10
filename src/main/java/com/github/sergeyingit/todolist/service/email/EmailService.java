package com.github.sergeyingit.todolist.service.email;

/**
 * Service for send mail message.
 */
public interface EmailService {
    /**
     * Send mail message.
     */
    void sendSimpleMailMessage(String to, String subject, String text);
}
