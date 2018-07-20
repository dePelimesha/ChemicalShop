package com.chemicalshop.validator;

import com.chemicalshop.entities.UsersEntity;
import com.chemicalshop.services.serviceinterface.ProductService;
import com.chemicalshop.services.serviceinterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AdminValidator implements Validator{
    @Autowired
    private UserService userService;

    public boolean supports(Class<?> aClass) {
        if (UsersEntity.class.isAssignableFrom(aClass))
            return true;
        else
            return false;
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        UsersEntity user = (UsersEntity) o;
        if (userService.getByUserName(user.getLogin()) != null) {
            errors.rejectValue("login", "login.exists");
        }
        if (!user.getLogin().matches("^admin\\d+")) {
            errors.rejectValue("login", "login.admin");
        }
        if (user.getPassword().length() < 4) {
            errors.rejectValue("password", "password.short");
        }
    }
}
