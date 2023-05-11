package com.demo.usermanager.domain.ports.api;

import com.demo.usermanager.domain.data.User;
import com.demo.usermanager.domain.exceptions.AlreadyRegisteredException;
import com.demo.usermanager.domain.exceptions.RegisterUserInputValidationsException;
import com.demo.usermanager.domain.exceptions.UserNotFoundException;

public interface UserManagerPort {

    void registerUser(User user) throws AlreadyRegisteredException, RegisterUserInputValidationsException;
    User getUser(String userId) throws UserNotFoundException;

}
