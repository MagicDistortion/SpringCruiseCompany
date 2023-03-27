package project.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordsSameValidator implements ConstraintValidator<PasswordsSameConstraint, Object> {
    @Override
    public void initialize(PasswordsSameConstraint passwordsSameConstraint) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext cxt) {
        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue("password");
        Object repasswordValue = new BeanWrapperImpl(value).getPropertyValue("repassword");

        if (passwordValue != null) return passwordValue.equals(repasswordValue);
        else return repasswordValue == null;
    }
}