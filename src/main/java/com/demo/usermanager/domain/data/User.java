package com.demo.usermanager.domain.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class User {

    private String id;
    private String email;
    private String password;
    private Person person;
    private String token;
    private boolean isActive;
    private Date lastLoginDate;
    private Date createdDate;
    private Date lastUpdateDate;

}
