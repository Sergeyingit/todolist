package com.github.sergeyingit.todolist.validation;

import com.github.sergeyingit.todolist.entity.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object userObject, ConstraintValidatorContext constraintValidatorContext) {
        User user = (User) userObject;
        return user.getPasswordNew().equals(user.getPasswordMatches());
    }
}
