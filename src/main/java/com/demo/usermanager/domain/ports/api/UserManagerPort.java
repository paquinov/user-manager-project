package com.demo.usermanager.domain.ports.api;

import com.demo.usermanager.domain.data.User;
import com.demo.usermanager.domain.exceptions.AlreadyRegisteredException;

public interface UserManagerPort {

    void registerUser(User user) throws AlreadyRegisteredException;

}
