package com.lando.notes_hexagonal_architecture_java.user.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lando.notes_hexagonal_architecture_java.security.auth.AuthenticationRequest;
import com.lando.notes_hexagonal_architecture_java.security.auth.AuthenticationResponse;
import com.lando.notes_hexagonal_architecture_java.security.config.Role;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.LoginDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
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
    void getAllUsers() throws JsonProcessingException {
        int expectedStatusCode = 200;
        response = configRequestGet("/api/user/list");

        assertThat(response.getStatusCode().value()).isEqualTo(expectedStatusCode);
        assertThat(response.getBody()).contains("userId").contains("name").contains("email").contains("password");
    }

    @Test
    void login() throws JsonProcessingException {
        String email = "test2@mail.com";
        String password = "1234";

        LoginDTO loginDTO = new LoginDTO(
                email,
                password
        );

        headers.add("Authorization", "Bearer " + getToken());

        HttpEntity<LoginDTO> entity = new HttpEntity<>(loginDTO, headers);
        response = restTemplate.exchange(createURLWithPort("/api/user/login"),
                HttpMethod.POST, entity, String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).contains("userId").contains("name").contains("email").contains("password")
                .contains("role");


        JsonObject convertedObject = new Gson().fromJson(response.getBody(), JsonObject.class);
        StringBuilder userName = new StringBuilder(convertedObject.get("name").toString());
        StringBuilder userEmail = new StringBuilder(convertedObject.get("email").toString());
        StringBuilder userPassword = new StringBuilder(convertedObject.get("password").toString());
        StringBuilder userRole = new StringBuilder(convertedObject.get("role").toString());
        userName.deleteCharAt(0);
        userName.deleteCharAt(userName.length() - 1);
        userEmail.deleteCharAt(0);
        userEmail.deleteCharAt(userEmail.length() - 1);
        userPassword.deleteCharAt(0);
        userPassword.deleteCharAt(userPassword.length() - 1);
        userRole.deleteCharAt(0);
        userRole.deleteCharAt(userRole.length() - 1);

        assertThat(userName.toString()).isEqualTo("Test user 2");
        assertThat(userEmail.toString()).isEqualTo(email);
        assertThat(userPassword.toString()).isEqualTo("$2a$10$qbnSCZH/4ZfWqFt7OWllBuN8iBsAXiXi2AlhsjW2G1QWmvyuybg/a");
        assertThat(userRole.toString()).isEqualTo("USER");
    }

    @Test
    void createUser() {
        UserDTO userDTO = new UserDTO(
                "Test user 4",
                "test4@mail.com",
                "test",
                Role.USER
        );

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        response = restTemplate.exchange(createURLWithPort("/api/user"),
                HttpMethod.POST, entity, String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(201);
        assertThat(response.getBody()).contains("token");
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