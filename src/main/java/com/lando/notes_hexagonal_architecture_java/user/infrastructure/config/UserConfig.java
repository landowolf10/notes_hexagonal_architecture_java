package com.lando.notes_hexagonal_architecture_java.user.infrastructure.config;

import com.lando.notes_hexagonal_architecture_java.user.domain.ports.api.UserServicePort;
import com.lando.notes_hexagonal_architecture_java.user.domain.ports.spi.UserPersistencePort;
import com.lando.notes_hexagonal_architecture_java.user.domain.service.UserService;
import com.lando.notes_hexagonal_architecture_java.user.infrastructure.adapters.UserJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserPersistencePort userPersistencePort(){
        return new UserJpaAdapter();
    }

    @Bean
    public UserServicePort userService(){
        return new UserService(userPersistencePort());
    }
}