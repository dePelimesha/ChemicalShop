package com.chemicalshop.validator;

import com.chemicalshop.entities.UsersEntity;
import com.chemicalshop.services.serviceinterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    public boolean supports(Class<?> aClass) {
        return UsersEntity.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "phoneNumber.required");
        UsersEntity user = (UsersEntity) o;
        if (userService.getByUserName(user.getLogin()) != null) {
            errors.rejectValue("login", "login.exists");
        }
        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "password.short");
        }
        if (!user.getPhoneNumber().matches("\\+\\d{12}")) {
            errors.rejectValue("phoneNumber", "phoneNumber.incorrect");
        }
        if (!user.getEmail().isEmpty() && !user.getEmail().matches(".+@\\w+\\.\\w+")) {
            errors.rejectValue("email", "email.incorrect");
        }
    }
}
