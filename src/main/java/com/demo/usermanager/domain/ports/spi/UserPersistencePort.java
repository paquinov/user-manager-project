package com.demo.usermanager.domain.ports.spi;

import com.demo.usermanager.domain.data.User;

import java.util.Optional;

public interface UserPersistencePort {

    void persistUser(User user);
    boolean emailIsAlreadyRegistered(String email);
    Optional<User> findUserById(String userId);

}
