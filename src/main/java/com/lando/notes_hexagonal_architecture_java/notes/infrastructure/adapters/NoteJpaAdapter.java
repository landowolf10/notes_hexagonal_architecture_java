package com.lando.notes_hexagonal_architecture_java.notes.infrastructure.adapters;

import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.NotesDTO;
import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.UpdateNoteDTO;
import com.lando.notes_hexagonal_architecture_java.notes.domain.ports.spi.NotePersistencePort;
import com.lando.notes_hexagonal_architecture_java.notes.infrastructure.entity.Notes;
import com.lando.notes_hexagonal_architecture_java.notes.infrastructure.repository.NoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteJpaAdapter implements NotePersistencePort
{
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Notes> getNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Notes getNoteById(Integer id) {
        Notes note = noteRepository.findNoteByNoteId(id);

        if(note == null)
            throw new EntityNotFoundException("Entity not found");

        return note;
    }

    @Override
    public List<Notes> getNotesByUserID(Integer userID) {

        List<Notes> notes = noteRepository.findNotesByUserId(userID);

        if(notes == null)
            throw new EntityNotFoundException("Entity not found");

        return notes;
    }

    @Override
    public Notes addNote(NotesDTO notesDTO) {

        Notes note = new ModelMapper().map(notesDTO, Notes.class);

        return noteRepository.save(note);
    }

    @Override
    public Notes updateNote(Integer id, UpdateNoteDTO notesDTO) {
        Notes note = getNoteById(id);

        new ModelMapper().map(notesDTO, note);

        noteRepository.save(note);

        return note;
    }

    @Override
    public void deleteNoteById(Integer id) {
        Notes note = getNoteById(id);
        noteRepository.delete(note);
    }
}