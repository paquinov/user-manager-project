package com.demo.usermanager.domain.jwt;

import com.demo.usermanager.domain.config.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final JwtProperties properties;

    public String generateToken(String email, Date startingDate) {
        return Jwts.builder().setClaims(new HashMap<>())
                            .setSubject(email)
                            .setIssuedAt(startingDate)
                            .setExpiration(new Date(startingDate.getTime() + properties.getExpirationTime()))
                            .signWith(SignatureAlgorithm.HS512, properties.getSecret())
                            .compact();
    }

}
