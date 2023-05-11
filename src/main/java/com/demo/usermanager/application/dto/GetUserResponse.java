package com.demo.usermanager.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GetUserResponse {

    private String id;
    private String email;
    private String name;
    private List<String> phoneList;
    private String created;
    private String modified;
    private String lastLogin;
    private boolean isActive;


}
