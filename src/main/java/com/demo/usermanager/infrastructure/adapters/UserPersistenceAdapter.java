package com.demo.usermanager.infrastructure.adapters;

import com.demo.usermanager.domain.data.User;
import com.demo.usermanager.domain.ports.spi.UserPersistencePort;
import com.demo.usermanager.infrastructure.entity.UserEntity;
import com.demo.usermanager.infrastructure.mappers.UserPersistenceMapper;
import com.demo.usermanager.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public void persistUser(User user) {
        UserEntity userEntity = userPersistenceMapper.buildUserEntity(user);
        userRepository.save(userEntity);
    }

    @Override
    public boolean emailIsAlreadyRegistered(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findUserById(String userId) {
        return userRepository.findById(userId)
                            .map(userPersistenceMapper::buildUser);
    }

}
