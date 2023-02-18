package com.lando.notes_hexagonal_architecture_java.user.application.controller;

import com.lando.notes_hexagonal_architecture_java.security.auth.AuthenticationResponse;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.LoginDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.UserDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.ports.api.UserServicePort;
import com.lando.notes_hexagonal_architecture_java.user.infrastructure.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController
{
    @Autowired
    UserServicePort userServicePort;

    @GetMapping("/list")
    List<User> getAllUsers() {
        return userServicePort.getUsers();
    }

    @PostMapping("/login")
    User login(@RequestBody LoginDTO loginDTO)
    {
        return userServicePort.login(loginDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    AuthenticationResponse createUser(@RequestBody UserDTO userDTO)
    {
        return userServicePort.addUser(userDTO);
    }
}