package com.demo.usermanager.application.controller;

import com.demo.usermanager.application.dto.ErrorResponse;
import com.demo.usermanager.domain.exceptions.AlreadyRegisteredException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponse> emailAlreadyRegistered() {
        return ResponseEntity.badRequest()
                            .body(ErrorResponse.builder()
                                                .message("El correo ya fue registrado")
                                                .build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> unknownIssue() {
        return ResponseEntity.internalServerError()
                            .body(ErrorResponse.builder()
                                                .message("Ocurrio un error inesperado")
                                                .build());
    }

}
