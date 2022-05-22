package com.demo.usermanager.application.controller;

import com.demo.usermanager.application.dto.RegisterUserRequest;
import com.demo.usermanager.application.dto.RegisterUserResponse;
import com.demo.usermanager.application.mappers.RegisterUserMapper;
import com.demo.usermanager.domain.data.User;
import com.demo.usermanager.domain.exceptions.RegisterUserInputValidationsException;
import com.demo.usermanager.domain.ports.api.UserManagerPort;
import com.demo.usermanager.domain.exceptions.AlreadyRegisteredException;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "Users API", description = "Manages the CRUD operations for the users")
public class UserController {

    private final UserManagerPort userManagerPort;
    private final RegisterUserMapper registerUserMapper;

    @PostMapping
    @ApiOperation(value = "Creates a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully created"),
            @ApiResponse(code = 400, message = "Some validation didn't pass"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest)
                                                            throws AlreadyRegisteredException, RegisterUserInputValidationsException {
        User user = registerUserMapper.buildUserFromRequest(registerUserRequest);
        userManagerPort.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                            .body(registerUserMapper.buildResponseFromUser(user));
    }

}
