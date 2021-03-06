package com.demo.usermanager.infrastructure.mappers;

import com.demo.usermanager.domain.data.Person;
import com.demo.usermanager.domain.data.User;
import com.demo.usermanager.infrastructure.entity.PersonEntity;
import com.demo.usermanager.infrastructure.entity.PhoneEntity;
import com.demo.usermanager.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserPersistenceMapper {

    public UserEntity buildUserEntity(User user) {
        Person person = user.getPerson();
        PersonEntity personEntity = PersonEntity.builder()
                                                .id(person.getId())
                                                .name(person.getName())
                                                .createdDate(person.getCreatedDate())
                                                .lastUpdateDate(person.getLastUpdateDate())
                                                .build();
        Set<PhoneEntity> phoneEntitySet = person.getPhoneList()
                                                .stream()
                                                .map(phone -> PhoneEntity.builder()
                                                                        .id(phone.getId())
                                                                        .number(phone.getNumber())
                                                                        .cityCode(phone.getCityCode())
                                                                        .countryCode(phone.getCountryCode())
                                                                        .isActive(phone.isActive())
                                                                        .person(personEntity)
                                                                        .createdDate(phone.getCreatedDate())
                                                                        .build())
                                                .collect(Collectors.toSet());
        personEntity.setPhoneEntitySet(phoneEntitySet);
        return UserEntity.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .password(user.getEncodedPassword())
                        .token(user.getToken())
                        .isActive(user.isActive())
                        .person(personEntity)
                        .lastLoginDate(user.getLastLoginDate())
                        .createdDate(user.getCreatedDate())
                        .lastUpdateDate(user.getLastUpdateDate())
                        .build();
    }

}
