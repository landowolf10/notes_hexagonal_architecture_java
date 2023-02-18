package com.lando.notes_hexagonal_architecture_java.user.infrastructure.config;

import com.lando.notes_hexagonal_architecture_java.security.config.JwtService;
import com.lando.notes_hexagonal_architecture_java.user.domain.ports.api.UserServicePort;
import com.lando.notes_hexagonal_architecture_java.user.domain.ports.spi.UserPersistencePort;
import com.lando.notes_hexagonal_architecture_java.user.domain.service.UserService;
import com.lando.notes_hexagonal_architecture_java.user.infrastructure.adapters.UserJpaAdapter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class UserConfig {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public UserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(jwtService, passwordEncoder);
    }

    @Bean
    public UserServicePort userService(){
        return new UserService(userPersistencePort());
    }
}