package com.lando.notes_hexagonal_architecture_java.notes.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.NotesDTO;
import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.UpdateNoteDTO;
import com.lando.notes_hexagonal_architecture_java.notes.infrastructure.adapters.NoteJpaAdapter;
import com.lando.notes_hexagonal_architecture_java.notes.infrastructure.entity.Notes;
import com.lando.notes_hexagonal_architecture_java.security.auth.AuthenticationRequest;
import com.lando.notes_hexagonal_architecture_java.security.auth.AuthenticationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteControllerTest {
    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    private ResponseEntity<String> response;

    String getToken() throws JsonProcessingException {
        AuthenticationRequest authRequest = new AuthenticationRequest(
                "test1@mail.com",
                "1234"
        );

        HttpEntity<AuthenticationRequest> entity = new HttpEntity<>(authRequest, headers);
        String authenticationRequest = restTemplate.exchange(createURLWithPort("/api/v1/auth/authenticate"),
                HttpMethod.POST, entity, String.class).getBody();

        AuthenticationResponse token = new ObjectMapper().readValue(authenticationRequest, AuthenticationResponse.class);

        return token.getToken();
    }

    @Test
    void getAllNotes() throws JsonProcessingException {
        int expectedStatusCode = 200;
        response = configRequestGet("/api/note/list");

        assertThat(response.getStatusCode().value()).isEqualTo(expectedStatusCode);
        assertThat(response.getBody()).contains("noteId").contains("userId").contains("owner").contains("title")
                .contains("content");
    }

    @Test
    void getNote() throws JsonProcessingException {
        int expectedStatusCode = 200;
        int noteId = 4;
        response = configRequestGet("/api/note/" + noteId);

        assertThat(response.getStatusCode().value()).isEqualTo(expectedStatusCode);
        assertThat(response.getBody()).contains("noteId").contains("userId").contains("owner").contains("title")
                .contains("content");

        Notes notes = new ObjectMapper().readValue(response.getBody(), Notes.class);
        assertThat(notes.getNoteId()).isEqualTo(noteId);
    }

    @Test
    void getUserNotes() throws JsonProcessingException {
        int expectedStatusCode = 200;
        int userId = 1;
        response = configRequestGet("/api/note/user-notes/" + userId);

        assertThat(response.getStatusCode().value()).isEqualTo(expectedStatusCode);
        assertThat(response.getBody()).contains("noteId").contains("userId").contains("owner").contains("title")
                .contains("content");

        Notes[] notes = new ObjectMapper().readValue(response.getBody(), Notes[].class);

        for (Notes note : notes) {
            assertThat(note.getUserId()).isEqualTo(userId);
            assertThat(note.getOwner()).isEqualTo("Test user 1");
        }
    }

    @Test
    void createNote() throws JsonProcessingException {
        int userId = 2;
        String owner = "Test user 2";
        String title = "Test";
        String content = "Test";

        NotesDTO noteRequest = new NotesDTO(
                userId,
                owner,
                title,
                content
        );

        headers.add("Authorization", "Bearer " + getToken());

        HttpEntity<NotesDTO> entity = new HttpEntity<>(noteRequest, headers);
        response = restTemplate.exchange(createURLWithPort("/api/note"),
                HttpMethod.POST, entity, String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(201);
        assertThat(response.getBody()).contains("noteId").contains("userId").contains("owner").contains("title")
                .contains("content");

        Notes notes = new ObjectMapper().readValue(response.getBody(), Notes.class);
        assertThat(notes.getUserId()).isEqualTo(userId);
        assertThat(notes.getOwner()).isEqualTo(owner);
        assertThat(notes.getTitle()).isEqualTo(title);
        assertThat(notes.getContent()).isEqualTo(content);
    }

    @Test
    void updateNote() throws JsonProcessingException {
        int noteId = 6;

        String owner = "Test user 2";
        String title = "Updated title";
        String content = "Content updated";

        UpdateNoteDTO noteRequestUpdate = new UpdateNoteDTO(
                owner,
                title,
                content
        );

        headers.add("Authorization", "Bearer " + getToken());

        HttpEntity<UpdateNoteDTO> entity = new HttpEntity<>(noteRequestUpdate, headers);
        response = restTemplate.exchange(createURLWithPort("/api/note/" + noteId),
                HttpMethod.PUT, entity, String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).contains("noteId").contains("userId").contains("owner").contains("title")
                .contains("content");

        Notes notes = new ObjectMapper().readValue(response.getBody(), Notes.class);
        assertThat(notes.getUserId()).isEqualTo(2);
        assertThat(notes.getOwner()).isEqualTo(owner);
        assertThat(notes.getTitle()).isEqualTo(title);
        assertThat(notes.getContent()).isEqualTo(content);
    }

    @Test
    void deleteNote() throws JsonProcessingException {
        int noteId = 2;
        NoteJpaAdapter noteJpaAdapter = new NoteJpaAdapter();
        headers.add("Authorization", "Bearer " + getToken());

        HttpEntity<UpdateNoteDTO> entity = new HttpEntity<>(null, headers);
        response = restTemplate.exchange(createURLWithPort("/api/note/" + noteId),
                HttpMethod.DELETE, entity, String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(204);
        Exception exception = assertThrows(Exception.class, () -> noteJpaAdapter.getNoteById(noteId));
        assertThat(exception.getMessage()).contains("because \"this.noteRepository\" is null");
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    ResponseEntity<String> configRequestGet(String endpoint) throws JsonProcessingException {
        headers.add("Authorization", "Bearer " + getToken());

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        return restTemplate.exchange(createURLWithPort(endpoint), HttpMethod.GET,
                entity, String.class);
    }
}