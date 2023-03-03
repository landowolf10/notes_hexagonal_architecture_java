package com.lando.notes_hexagonal_architecture_java.user.domain.ports.api;

import com.lando.notes_hexagonal_architecture_java.security.auth.AuthenticationResponse;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.LoginDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.UserDTO;
import com.lando.notes_hexagonal_architecture_java.user.infrastructure.entity.Users;

import java.util.List;

public interface UserServicePort
{
    List<Users> getUsers();

    Users login(LoginDTO loginDTO);

    AuthenticationResponse addUser(UserDTO notesDTO);
}