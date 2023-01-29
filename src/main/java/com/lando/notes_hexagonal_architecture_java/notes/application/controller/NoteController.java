package com.lando.notes_hexagonal_architecture_java.notes.application.controller;

import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.NotesDTO;
import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.UpdateNoteDTO;
import com.lando.notes_hexagonal_architecture_java.notes.domain.ports.api.NoteServicePort;
import com.lando.notes_hexagonal_architecture_java.notes.infrastructure.entity.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
public class NoteController
{
    @Autowired
    NoteServicePort noteServicePort;

    @GetMapping("/list")
    List<Notes> getAllNotes() {
        return noteServicePort.getNotes();
    }

    @GetMapping("/{id}")
    Notes getNote(@PathVariable Integer id)
    {
        return noteServicePort.getNoteById(id);
    }

    @GetMapping("/user-notes/{userID}")
    List<Notes> getUserNotes(@PathVariable Integer userID)
    {
        return noteServicePort.getNotesByUserID(userID);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Notes createNote(@RequestBody NotesDTO notesDTO)
    {
        return noteServicePort.addNote(notesDTO);
    }

    @PutMapping("/{id}")
    Notes updateNote(
            @PathVariable Integer id,
            @RequestBody UpdateNoteDTO notesDTO
    )
    {
        return noteServicePort.updateNote(id, notesDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteNote(@PathVariable Integer id) {
        noteServicePort.deleteNoteById(id);
    }
}