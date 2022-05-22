package com.demo.usermanager.domain.services;

import com.demo.usermanager.domain.data.Person;
import com.demo.usermanager.domain.data.Phone;
import com.demo.usermanager.domain.data.User;
import com.demo.usermanager.domain.exceptions.RegisterUserInputValidationsException;
import com.demo.usermanager.domain.ports.api.UserManagerPort;
import com.demo.usermanager.domain.exceptions.AlreadyRegisteredException;
import com.demo.usermanager.domain.ports.spi.UserPersistencePort;
import com.demo.usermanager.domain.validators.RegisterUserInputValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserManagerImpl implements UserManagerPort {

    private final UserPersistencePort userPersistencePort;
    private final RegisterUserInputValidator inputValidator;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(User user) throws AlreadyRegisteredException, RegisterUserInputValidationsException {
        executeBusinessValidations(user);
        updateInformationByBusinessLogic(user);
        userPersistencePort.persistUser(user);
    }

    private void executeBusinessValidations(User user) throws AlreadyRegisteredException, RegisterUserInputValidationsException {
        validateInput(user);
        validateRegisteredEmail(user.getEmail());
    }

    private void validateInput(User user) throws RegisterUserInputValidationsException {
        List<String> violations = inputValidator.validate(user);
        if (!violations.isEmpty()) {
            log.warn("Business logic validations - Some input values are not right");
            throw new RegisterUserInputValidationsException(violations);
        }
    }

    private void validateRegisteredEmail(String email) throws AlreadyRegisteredException {
        if (userPersistencePort.emailIsAlreadyRegistered(email)) {
            log.warn("Business logic validations - Email {} is already registered", email);
            throw new AlreadyRegisteredException();
        }
    }

    private void updateInformationByBusinessLogic(User user) {
        Date now = new Date();
        updateUserInformation(user, now);
        Person person = user.getPerson();
        updatePersonInformation(person, now);
        person.getPhoneList().forEach(phone -> updatePhoneInformation(phone, now));
    }

    private void updateUserInformation(User user, Date createdDate) {
        user.setId(UUID.randomUUID().toString());
        user.setActive(true);
        user.setEncodedPassword(passwordEncoder.encode(user.getPassword()));
        user.setToken("GENERIC-TOKEN"); // TODO: generate and set Token
        user.setLastLoginDate(createdDate);
        user.setCreatedDate(createdDate);
        user.setLastUpdateDate(createdDate);
    }

    private void updatePersonInformation(Person person, Date createdDate) {
        person.setId(UUID.randomUUID().toString());
        person.setCreatedDate(createdDate);
        person.setLastUpdateDate(createdDate);
    }

    private void updatePhoneInformation(Phone phone, Date createdDate) {
        phone.setId(UUID.randomUUID().toString());
        phone.setActive(true);
        phone.setCreatedDate(createdDate);
    }

}
