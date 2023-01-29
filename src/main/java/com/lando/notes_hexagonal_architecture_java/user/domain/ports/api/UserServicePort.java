package com.lando.notes_hexagonal_architecture_java.user.domain.ports.api;

import com.lando.notes_hexagonal_architecture_java.user.domain.dto.LoginDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.UserDTO;
import com.lando.notes_hexagonal_architecture_java.user.infrastructure.entity.User;

import java.util.List;

public interface UserServicePort
{
    List<User> getUsers();

    User login(LoginDTO loginDTO);

    User addUser(UserDTO notesDTO);
}