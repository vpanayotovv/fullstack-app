package com.project.cocktailapp.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CustomValidationImpl.class)
public @interface CustomValidation {
    String message() default "Follow the pattern";

    String regex();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
