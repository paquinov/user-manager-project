package com.demo.usermanager.domain.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "register-user.input-validations")
public class RegisterUserInputValidationProperties {

    private String passwordPattern;

}
