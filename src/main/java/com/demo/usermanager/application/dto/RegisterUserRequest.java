package com.demo.usermanager.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterUserRequest {

    private String name;
    private String email;
    private String password;
    private List<PhoneRequest> phones;

}
