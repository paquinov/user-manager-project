package com.demo.usermanager.domain.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Builder
public class User {

    private String id;
    @Email(message = "El campo email no cumple el formato")
    @NotBlank(message = "El campo email es obligatorio")
    @Size(max = 255, message = "El campo email no puede exceder los 255 caracteres")
    private String email;
    @NotEmpty(message = "El campo password es obligatorio")
    private String password;
    @Valid
    private Person person;
    private String token;
    private boolean isActive;
    private Date lastLoginDate;
    private Date createdDate;
    private Date lastUpdateDate;

}
