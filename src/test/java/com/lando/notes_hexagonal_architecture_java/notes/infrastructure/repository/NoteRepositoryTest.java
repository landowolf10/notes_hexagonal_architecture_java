package com.lando.notes_hexagonal_architecture_java.notes.infrastructure.repository;

import com.lando.notes_hexagonal_architecture_java.notes.infrastructure.entity.Notes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class NoteRepositoryTest {
    String noteContent = "Test content";
    String userName = "Test user 1";
    int noteId = 5;
    int userId = 1;

    @Autowired
    private NoteRepository noteRepository;

    @Test
    void itShouldCreateNewNote() {
        Notes notes = new Notes(
                noteId,
                userId,
                userName,
                "Repo Test",
                noteContent

        );

        noteRepository.save(notes);

        //When
        Notes note = noteRepository.findNoteByNoteId(noteId);

        //Then
        assertThat(note.getNoteId()).isEqualTo(noteId);
        assertThat(note.getContent()).isEqualTo(noteContent);
    }

    @Test
    void itShouldGetNoteById() {
        //When
        Notes note = noteRepository.findNoteByNoteId(noteId);

        //Then
        assertThat(note.getNoteId()).isEqualTo(noteId);
        assertThat(note.getContent()).isEqualTo(noteContent);
    }

    @Test
    void itShouldGetNotesByUserID() {
        //When
        List<Notes> note = noteRepository.findNotesByUserId(userId);

        //Then
        for (Notes notes : note) {
            assertThat(notes.getOwner()).isEqualTo(userName);
            assertThat(notes.getUserId()).isEqualTo(userId);
        }
    }

    @Test
    void itShouldUpdateNote() {
        //Given
        Notes notes = new Notes(
                noteId,
                2,
                "Test user 2",
                "Updated",
                "Updated content"

        );

        noteRepository.save(notes);

        //When
        Notes note = noteRepository.findNoteByNoteId(noteId);

        //Then
        assertThat(note.getNoteId()).isEqualTo(noteId);
        assertThat(note.getContent()).isEqualTo("Updated content");
    }

    @Test
    void itShouldDeleteNote() {
        //When
        noteRepository.deleteById(noteId);

        //Then
        assertThatThrownBy(() -> noteRepository.deleteById(noteId))
                .isInstanceOf(EmptyResultDataAccessException.class)
                .hasMessageContaining("entity with id " + noteId);
    }
}