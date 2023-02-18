package com.lando.notes_hexagonal_architecture_java.user.domain.service;

import com.lando.notes_hexagonal_architecture_java.security.auth.AuthenticationResponse;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.LoginDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.UserDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.ports.api.UserServicePort;
import com.lando.notes_hexagonal_architecture_java.user.domain.ports.spi.UserPersistencePort;
import com.lando.notes_hexagonal_architecture_java.user.infrastructure.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService implements UserServicePort
{
    private final UserPersistencePort userPersistencePort;

    public UserService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public List<User> getUsers() {
        return userPersistencePort.getUsers();
    }

    @Override
    public User login(LoginDTO loginDTO) {
        return userPersistencePort.login(loginDTO);
    }

    @Override
    public AuthenticationResponse addUser(UserDTO userDTO) {
        return userPersistencePort.addUser(userDTO);
    }
}