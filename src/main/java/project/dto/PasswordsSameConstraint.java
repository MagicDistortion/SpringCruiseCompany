package project.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordsSameValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordsSameConstraint {
    String message() default "Passwords must be same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}