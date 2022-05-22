package com.demo.usermanager.application.controller;

import com.demo.usermanager.application.dto.RegisterUserRequest;
import com.demo.usermanager.application.dto.RegisterUserResponse;
import com.demo.usermanager.application.mappers.RegisterUserMapper;
import com.demo.usermanager.domain.data.User;
import com.demo.usermanager.domain.exceptions.RegisterUserInputValidationsException;
import com.demo.usermanager.domain.ports.api.UserManagerPort;
import com.demo.usermanager.domain.exceptions.AlreadyRegisteredException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserManagerPort userManagerPort;
    private final RegisterUserMapper registerUserMapper;

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest)
                                                            throws AlreadyRegisteredException, RegisterUserInputValidationsException {
        User user = registerUserMapper.buildUserFromRequest(registerUserRequest);
        userManagerPort.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                            .body(registerUserMapper.buildResponseFromUser(user));
    }

}
