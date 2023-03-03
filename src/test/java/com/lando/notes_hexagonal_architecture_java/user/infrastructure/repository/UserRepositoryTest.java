package com.lando.notes_hexagonal_architecture_java.user.infrastructure.repository;

import com.lando.notes_hexagonal_architecture_java.security.config.Role;
import com.lando.notes_hexagonal_architecture_java.user.infrastructure.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private final String email = "test1@mail.com";

    @Test
    void itShouldFindUserByEmailAndPassword() {
        //When
        Users user = userRepository.findUserByEmailAndPassword(
          email,
          "$2a$10$ZZyFEmuFw3GT9kLAcOyPduvlvleSyEqG06k.h1iiNl8NiQoNpEJi."
        );

        //Then
        assertThat(user.getUserId()).isEqualTo(1);
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    void itShouldFindUserByEmail() {
        //When
        Optional<Users> user = userRepository.findUserByEmail(email);

        //Then
        assertThat(user.get().getUserId()).isEqualTo(1);
        assertThat(user.get().getEmail()).isEqualTo(email);
    }

    @Test
    void itShouldCreateNewUser() {
        String email = "test3@mail.com";
        String name = "Test user 3";

        Users user = new Users(
                3,
                name,
                email,
                "Test",
                Role.USER
        );

        userRepository.save(user);

        //When
        Optional<Users> users = userRepository.findUserByEmail(email);

        //Then
        assertThat(users.get().getEmail()).isEqualTo(email);
        assertThat(users.get().getName()).isEqualTo(name);
    }
}