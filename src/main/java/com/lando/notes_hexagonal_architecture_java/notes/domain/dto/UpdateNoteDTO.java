package com.lando.notes_hexagonal_architecture_java.notes.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNoteDTO
{
    private String owner;
    private String title;
    private String content;
}