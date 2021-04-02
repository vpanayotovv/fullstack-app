package com.project.cocktailapp.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomValidationImpl implements ConstraintValidator<CustomValidation,String> {

    private String regex;

    @Override
    public void initialize(CustomValidation constraintAnnotation) {
        this.regex = constraintAnnotation.regex();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (String line : value.split("\\r?\\n")) {
            if (!line.matches(regex)){
                return false;
            }
        }
        return true;
    }
}
