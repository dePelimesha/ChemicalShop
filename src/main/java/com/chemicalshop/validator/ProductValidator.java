package com.chemicalshop.validator;

import com.chemicalshop.entities.ProductsEntity;
import com.chemicalshop.services.serviceinterface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    @Autowired
    private ProductService productService;

    public boolean supports(Class<?> aClass) {
        return ProductsEntity.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "value.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inStock", "value.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "molarMass", "value.required");
        ProductsEntity product = (ProductsEntity) o;
        if (productService.getByName(product.getProductName()) != null) {
            errors.rejectValue("productName", "product.exists");
        }
    }
}
