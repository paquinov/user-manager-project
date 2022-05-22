package com.demo.usermanager.domain.validators;

import com.demo.usermanager.domain.config.RegisterUserInputValidationProperties;
import com.demo.usermanager.domain.data.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RegisterUserInputValidator {

    private final Validator validator;
    private final RegisterUserInputValidationProperties properties;

    public List<String> validate(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage)
                                                        .collect(Collectors.toList());
        validatePassword(user.getPassword(), errorMessages);
        return errorMessages;
    }

    public void validatePassword(String password, List<String> errorMessages) {
        Pattern pattern = Pattern.compile(properties.getPasswordPattern());
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            errorMessages.add("Contrasenia no cumple con el patron establecido");
        }
    }

}
