package com.demo.usermanager.application.controller;

import com.demo.usermanager.application.dto.ErrorResponse;
import com.demo.usermanager.domain.exceptions.AlreadyRegisteredException;
import com.demo.usermanager.domain.exceptions.RegisterUserInputValidationsException;
import com.demo.usermanager.domain.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ResponseExceptionHandler {

    @ExceptionHandler(RegisterUserInputValidationsException.class)
    public ResponseEntity<Set<ErrorResponse>> registerUserInputValidations(RegisterUserInputValidationsException ex) {
        return ResponseEntity.badRequest()
                .body(ex.getErrorMessages().stream().map(violation -> ErrorResponse.builder()
                        .message(violation)
                        .build()).collect(Collectors.toSet()));
    }

    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponse> emailAlreadyRegistered(AlreadyRegisteredException ex) {
        return ResponseEntity.badRequest()
                            .body(ErrorResponse.builder()
                                                .message("El correo ya fue registrado")
                                                .build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userWasNotFound(UserNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> unknownIssue(RuntimeException ex) {
        log.error("Unknown Issue", ex);
        return ResponseEntity.internalServerError()
                            .body(ErrorResponse.builder()
                                                .message("Ocurrio un error inesperado")
                                                .build());
    }

}
