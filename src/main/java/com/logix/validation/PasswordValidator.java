package com.logix.validation;

import com.logix.persistence.dto.UserDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordValidator implements ConstraintValidator<ValidPassword, Object>{
    @Override
    public void initialize(final ValidPassword constraintAnnotation){    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context){
        final UserDto user = (UserDto) obj;
        return user.getPass().equals(user.getCpass());
    }
}
