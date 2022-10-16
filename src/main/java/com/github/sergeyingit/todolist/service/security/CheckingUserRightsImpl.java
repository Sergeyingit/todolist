package com.github.sergeyingit.todolist.service.security;

import com.github.sergeyingit.todolist.entity.User;
import com.github.sergeyingit.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CheckingUserRightsImpl implements CheckingUserRights{

    @Autowired
    private UserService userService;

    @Override
    public AccessType getAccessType(Authentication authentication, int requestedUserId) {

        String name = authentication.getName();
        User currentUser = userService.findByEmail(name);
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a-> a.getAuthority().equals("ROLE_ADMIN"));

        User requestedUser = userService.findById(requestedUserId);

        if (currentUser != null && currentUser.equals(requestedUser) || isAdmin && requestedUser != null) {
            return AccessType.ALLOWED;
        } else if (currentUser != null && requestedUser != null) {
            return AccessType.DENIED;
        }

        return AccessType.NOT_FOUND;
    }
}
