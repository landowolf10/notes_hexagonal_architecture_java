package com.lando.notes_hexagonal_architecture_java.user.domain.ports.spi;

import com.lando.notes_hexagonal_architecture_java.security.auth.AuthenticationResponse;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.LoginDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.UserDTO;
import com.lando.notes_hexagonal_architecture_java.user.infrastructure.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistencePort
{
    List<User> getUsers();

    User login(LoginDTO loginDTO);

    AuthenticationResponse addUser(UserDTO notesDTO);
}
