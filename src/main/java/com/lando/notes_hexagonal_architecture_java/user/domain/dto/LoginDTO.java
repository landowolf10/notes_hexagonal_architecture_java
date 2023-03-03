package com.lando.notes_hexagonal_architecture_java.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO
{
    private String email;
    private String password;
}