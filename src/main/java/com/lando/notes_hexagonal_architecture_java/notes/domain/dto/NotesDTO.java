package com.lando.notes_hexagonal_architecture_java.notes.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotesDTO
{
    private Integer userId;
    private String owner;
    private String title;
    private String content;
}