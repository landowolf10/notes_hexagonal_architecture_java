package com.lando.notes_hexagonal_architecture_java.user.domain.dto;

import lombok.Data;

@Data
public class LoginDTO
{
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}