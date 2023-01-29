package com.lando.notes_hexagonal_architecture_java.notes.domain.service;

import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.NotesDTO;
import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.UpdateNoteDTO;
import com.lando.notes_hexagonal_architecture_java.notes.domain.ports.api.NoteServicePort;
import com.lando.notes_hexagonal_architecture_java.notes.domain.ports.spi.NotePersistencePort;
import com.lando.notes_hexagonal_architecture_java.notes.infrastructure.entity.Notes;

import java.util.List;

public class NoteService implements NoteServicePort
{
    private final NotePersistencePort notePersistencePort;

    public NoteService(NotePersistencePort notePersistencePort) {
        this.notePersistencePort = notePersistencePort;
    }

    @Override
    public List<Notes> getNotes() {
        return notePersistencePort.getNotes();
    }

    @Override
    public Notes getNoteById(Integer id) {
        return notePersistencePort.getNoteById(id);
    }

    @Override
    public List<Notes> getNotesByUserID(Integer userID) {
        return notePersistencePort.getNotesByUserID(userID);
    }

    @Override
    public Notes addNote(NotesDTO notesDTO) {
        return notePersistencePort.addNote(notesDTO);
    }

    @Override
    public Notes updateNote(Integer id, UpdateNoteDTO notesDTO) {
        return notePersistencePort.updateNote(id, notesDTO);
    }

    @Override
    public void deleteNoteById(Integer id) {
        notePersistencePort.deleteNoteById(id);
    }
}