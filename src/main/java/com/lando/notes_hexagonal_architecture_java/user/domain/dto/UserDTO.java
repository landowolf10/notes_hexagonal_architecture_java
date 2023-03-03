package com.lando.notes_hexagonal_architecture_java.user.domain.dto;

import com.lando.notes_hexagonal_architecture_java.security.config.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO
{
    private String name;
    private String email;
    private String password;
    private Role role;
}