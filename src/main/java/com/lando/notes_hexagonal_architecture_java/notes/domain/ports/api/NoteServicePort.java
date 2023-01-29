package com.lando.notes_hexagonal_architecture_java.notes.domain.ports.api;

import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.NotesDTO;
import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.UpdateNoteDTO;
import com.lando.notes_hexagonal_architecture_java.notes.infrastructure.entity.Notes;

import java.util.List;

public interface NoteServicePort
{
    List<Notes> getNotes();

    Notes getNoteById(Integer id);
    List<Notes> getNotesByUserID(Integer userID);

    Notes addNote(NotesDTO notesDTO);

    Notes updateNote(Integer id, UpdateNoteDTO notesDTO);

    void deleteNoteById(Integer id);
}