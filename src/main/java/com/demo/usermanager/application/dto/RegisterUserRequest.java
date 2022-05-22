package com.demo.usermanager.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(description = "User registration request body")
public class RegisterUserRequest {

    @ApiModelProperty(notes = "Name of the new user", position = 1, example = "Juan Rodriguez")
    private String name;
    @ApiModelProperty(notes = "Email of the new user", position = 2, example = "juan@rodriguez.org")
    private String email;
    @ApiModelProperty(notes = "Password for the new user", position = 3, example = "abcde123")
    private String password;
    @ApiModelProperty(notes = "Phone list for the new user", position = 4)
    private List<PhoneRequest> phones;

}
