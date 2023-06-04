package com.example.tuan5_ketnoicsdl.validator;
import com.example.tuan5_ketnoicsdl.repository.IuserRepository;
import com.example.tuan5_ketnoicsdl.validator.annotation.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
public class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String> {
    @Autowired
    private IuserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context){
        if(userRepository == null)
            return true;
        return userRepository.findByUsername(username) == null;
    }
}
