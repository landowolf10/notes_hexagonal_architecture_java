package com.lando.notes_hexagonal_architecture_java.user.domain.service;

import com.lando.notes_hexagonal_architecture_java.notes.domain.dto.UpdateNoteDTO;
import com.lando.notes_hexagonal_architecture_java.security.config.Role;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.LoginDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.UserDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.ports.spi.UserPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTest {
    @Mock private UserPersistencePort userPersistencePort;

    @Test
    void canGetAllUsers() {
        //When
        userPersistencePort.getUsers();

        //Then
        verify(userPersistencePort).getUsers();
    }

    @Test
    void canLogin() {
        //Given
        LoginDTO loginDTO = new LoginDTO("test1@mail.com", "1234");

        //When
        userPersistencePort.login(loginDTO);

        //Then
        ArgumentCaptor<LoginDTO> loginDTOArgumentCaptor = ArgumentCaptor.forClass(LoginDTO.class);
        verify(userPersistencePort).login(loginDTOArgumentCaptor.capture());

        LoginDTO captureLogin = loginDTOArgumentCaptor.getValue();

        assertThat(captureLogin).isEqualTo(loginDTO);
    }

    @Test
    void addUser() {
        //Given
        UserDTO userDTO = new UserDTO(
                "Test user",
                "test_mail@gmail.com",
                "1234",
                Role.USER
        );

        //When
        userPersistencePort.addUser(userDTO);

        //Then
        ArgumentCaptor<UserDTO> userDTOArgumentCaptor = ArgumentCaptor.forClass(UserDTO.class);
        verify(userPersistencePort).addUser(userDTOArgumentCaptor.capture());

        UserDTO captureLogin = userDTOArgumentCaptor.getValue();

        assertThat(captureLogin).isEqualTo(userDTO);
    }
}