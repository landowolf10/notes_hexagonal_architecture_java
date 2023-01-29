package com.lando.notes_hexagonal_architecture_java.user.domain.dto;

import lombok.Data;

@Data
public class UserDTO
{
    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}