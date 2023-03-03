package com.lando.notes_hexagonal_architecture_java.notes.domain.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ErrorDetailDTO {
    private long timeStamp;
    private int status;
    private String title;
    private String detail;

    public Map<String, List<ValidationErrorDTO>> getErrors() {
        return errors;
    }

    private Map<String, List<ValidationErrorDTO>> errors = new HashMap<>();
}