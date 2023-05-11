package com.demo.usermanager.application.mappers;

import com.demo.usermanager.application.dto.GetUserResponse;
import com.demo.usermanager.domain.data.Person;
import com.demo.usermanager.domain.data.Phone;
import com.demo.usermanager.domain.data.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GetUserMapper {

    public GetUserResponse buildUserResponseFromUser(User user) {
        Person person = user.getPerson();
        return GetUserResponse.builder()
                            .id(user.getId())
                            .email(user.getEmail())
                            .name(person.getName())
                            .phoneList(person.getPhoneList()
                                            .stream()
                                            .map(Phone::getNumber)
                                            .collect(Collectors.toList()))
                            .created(user.getCreatedDate().toString())
                            .modified(user.getLastUpdateDate().toString())
                            .lastLogin(user.getLastLoginDate().toString())
                            .isActive(user.isActive())
                            .build();
    }

}
