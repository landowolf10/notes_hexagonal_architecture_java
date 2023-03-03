package com.lando.notes_hexagonal_architecture_java.notes.domain.service;

import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.NotesDTO;
import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.UpdateNoteDTO;
import com.lando.notes_hexagonal_architecture_java.notes.domain.ports.spi.NotePersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class NoteServiceTest {
    @Mock private NotePersistencePort notePersistencePort;

    @Test
    void canGetAllNotes() {
            //When
            notePersistencePort.getNotes();

            //Then
            verify(notePersistencePort).getNotes();
    }

    @Test
    void canGetNoteById() {
        //When
        notePersistencePort.getNoteById(3);

        //Then
        verify(notePersistencePort).getNoteById(3);
    }

    @Test
    void canGetNotesByUserId() {
        //When
        notePersistencePort.getNotesByUserID(1);

        //Then
        verify(notePersistencePort).getNotesByUserID(1);
    }

    @Test
    void canAddNote() {
        //Given
        String noteContent = "Test content";

        NotesDTO notesDTO = new NotesDTO(
                2,
                "Test user 2",
                "Test",
                noteContent

        );

        //When
        notePersistencePort.addNote(notesDTO);

        //Then
        ArgumentCaptor<NotesDTO> notesDTOArgumentCaptor = ArgumentCaptor.forClass(NotesDTO.class);

        verify(notePersistencePort).addNote(notesDTOArgumentCaptor.capture());

        NotesDTO captureNote = notesDTOArgumentCaptor.getValue();

        assertThat(captureNote).isEqualTo(notesDTO);
    }

    @Test
    void updateNote() {
        //Given
        String noteContent = "Content updated";
        String noteTitle = "Title updated";

        UpdateNoteDTO updateNoteDTO = new UpdateNoteDTO(
                "Test user 2",
                noteTitle,
                noteContent

        );

        //When
        notePersistencePort.updateNote(2, updateNoteDTO);

        //Then
        ArgumentCaptor<UpdateNoteDTO> updateNoteDTOArgumentCaptor = ArgumentCaptor.forClass(UpdateNoteDTO.class);

        verify(notePersistencePort).updateNote(any(), updateNoteDTOArgumentCaptor.capture());

        UpdateNoteDTO captureUpdatedNote = updateNoteDTOArgumentCaptor.getValue();

        assertThat(captureUpdatedNote).isEqualTo(updateNoteDTO);
    }

    @Test
    void deleteNoteById() {
        //When
        notePersistencePort.deleteNoteById(1);

        //Then
        verify(notePersistencePort).deleteNoteById(1);
    }
}