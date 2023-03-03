package com.lando.notes_hexagonal_architecture_java.user.domain.ports.spi;

import com.lando.notes_hexagonal_architecture_java.security.auth.AuthenticationResponse;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.LoginDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.UserDTO;
import com.lando.notes_hexagonal_architecture_java.user.infrastructure.entity.Users;

import java.util.List;

public interface UserPersistencePort
{
    List<Users> getUsers();

    Users login(LoginDTO loginDTO);

    AuthenticationResponse addUser(UserDTO notesDTO);
}