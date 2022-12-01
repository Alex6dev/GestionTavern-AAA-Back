package aaa.tavern.dto.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import aaa.tavern.dto.PlayerDto;
import aaa.tavern.dto.annotation.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        PlayerDto player = (PlayerDto) obj;
        return player.getPassword().equals(player.getMatchingPassword());
    }
}
