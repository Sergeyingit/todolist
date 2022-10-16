package com.github.sergeyingit.todolist.service.security;

import com.github.sergeyingit.todolist.entity.User;
import org.springframework.security.core.Authentication;

/**
 * Service for checking user rights by id in url.
 */
public interface CheckingUserRights {

    AccessType getAccessType(Authentication authentication, int requestedUserId);
}
