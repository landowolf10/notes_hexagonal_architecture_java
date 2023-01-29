package com.lando.notes_hexagonal_architecture_java.notes.domain.dto;

import lombok.Data;

@Data
public class ValidationErrorDTO {
    private String code;
    private String message;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
