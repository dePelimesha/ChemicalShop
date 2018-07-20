package com.chemicalshop.validator;

import com.chemicalshop.entities.OrderinfoEntity;
import com.chemicalshop.entities.UsersEntity;
import com.chemicalshop.services.serviceinterface.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OrderValidator implements Validator {

    @Autowired
    OrderInfoService orderInfoService;

    @Override
    public boolean supports(Class<?> aClass) {
        if (UsersEntity.class.isAssignableFrom(aClass) || OrderinfoEntity.class.isAssignableFrom(aClass))
            return true;
        else
            return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "phoneNumber.required");
        OrderinfoEntity order = (OrderinfoEntity) o;
        if (!order.getPhoneNumber().matches("\\+\\d{12}")) {
            errors.rejectValue("phoneNumber", "phoneNumber.incorrect");
        }
        if (!order.getEmail().isEmpty() && !order.getEmail().matches(".+@\\w+\\.\\w+")) {
            errors.rejectValue("email", "email.incorrect");
        }
    }
}
