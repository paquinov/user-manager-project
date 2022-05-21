package com.demo.usermanager.domain.ports.spi;

import com.demo.usermanager.domain.data.User;

public interface UserPersistencePort {

    void persistUser(User user);
    boolean emailIsAlreadyRegistered(String email);

}
