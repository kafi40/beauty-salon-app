package ru.kafi.beautysalonapigateway.annotation;

import jakarta.validation.Constraint;
import ru.kafi.beautysalonapigateway.validator.PositiveListValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PositiveListValidator.class)
public @interface PositiveList {
    String message() default "ID не может быть меньше 1";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
