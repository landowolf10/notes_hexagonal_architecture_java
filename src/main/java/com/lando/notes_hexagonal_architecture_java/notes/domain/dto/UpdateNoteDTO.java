package com.lando.notes_hexagonal_architecture_java.notes.domain.dto;

import lombok.Data;

@Data
public class UpdateNoteDTO
{
    private String owner;
    private String title;
    private String content;

    public String getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}