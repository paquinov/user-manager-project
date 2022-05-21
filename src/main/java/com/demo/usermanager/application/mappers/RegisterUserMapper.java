package com.demo.usermanager.application.mappers;

import com.demo.usermanager.application.dto.RegisterUserRequest;
import com.demo.usermanager.application.dto.RegisterUserResponse;
import com.demo.usermanager.domain.data.Person;
import com.demo.usermanager.domain.data.Phone;
import com.demo.usermanager.domain.data.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RegisterUserMapper {

    public User buildUserFromRequest(RegisterUserRequest registerUserRequest) {
        return User.builder()
                    .email(registerUserRequest.getEmail())
                    .password(registerUserRequest.getPassword())
                    .person(Person.builder()
                                .name(registerUserRequest.getName())
                                .phoneList(registerUserRequest.getPhones()
                                                            .stream()
                                                            .map(phone -> Phone.builder()
                                                                            .number(phone.getNumber())
                                                                            .cityCode(phone.getCityCode())
                                                                            .countryCode(phone.getCountryCode())
                                                                            .build())
                                                            .collect(Collectors.toList()))
                                .build())
                    .build();
    }

    public RegisterUserResponse buildResponseFromUser(User user) {
        return RegisterUserResponse.builder()
                                    .id(user.getId())
                                    .created(user.getCreatedDate())
                                    .modified(user.getLastUpdateDate())
                                    .lastLogin(user.getLastLoginDate())
                                    .token(user.getToken())
                                    .isActive(user.isActive())
                                    .build();
    }

}
