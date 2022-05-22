package com.demo.usermanager.domain.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt-configuration")
public class JwtProperties {

    private String secret;
    private long expirationTime;

}
