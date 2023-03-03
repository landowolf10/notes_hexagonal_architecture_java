package com.lando.notes_hexagonal_architecture_java.notes.domain.dto;

import lombok.Data;

@Data
public class ValidationErrorDTO {
    private String code;
    private String message;
}
