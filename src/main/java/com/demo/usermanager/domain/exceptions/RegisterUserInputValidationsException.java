package com.demo.usermanager.domain.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class RegisterUserInputValidationsException extends Exception {

    private final List<String> errorMessages;

    public RegisterUserInputValidationsException(List<String> errorMessages) {
        super();
        this.errorMessages = errorMessages;
    }

}
