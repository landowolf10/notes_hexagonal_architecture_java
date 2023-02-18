package com.lando.notes_hexagonal_architecture_java.user.infrastructure.adapters;

import com.lando.notes_hexagonal_architecture_java.security.auth.AuthenticationResponse;
import com.lando.notes_hexagonal_architecture_java.security.config.JwtService;
import com.lando.notes_hexagonal_architecture_java.security.config.Role;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.LoginDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.UserDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.ports.spi.UserPersistencePort;
import com.lando.notes_hexagonal_architecture_java.user.infrastructure.entity.User;
import com.lando.notes_hexagonal_architecture_java.user.infrastructure.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserJpaAdapter implements UserPersistencePort
{
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {

        return userRepository.findAll();
    }

    @Override
    public User login(LoginDTO loginDTO) {
        String encryptedPassword = Objects.requireNonNull(userRepository.findUserByEmail(loginDTO.getEmail())
                .orElse(null)).getPassword();

        User user = null;

        if (passwordEncoder.matches(loginDTO.getPassword(), encryptedPassword))
            user = userRepository.findUserByEmailAndPassword(loginDTO.getEmail(), encryptedPassword);

        if(user == null)
            throw new EntityNotFoundException("User not registered");

        return user;
    }

    @Override
    public AuthenticationResponse addUser(UserDTO userDTO) {
        var user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}