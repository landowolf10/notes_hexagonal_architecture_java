package com.lando.notes_hexagonal_architecture_java.notes.infrastructure.config;

import com.lando.notes_hexagonal_architecture_java.notes.domain.ports.api.NoteServicePort;
import com.lando.notes_hexagonal_architecture_java.notes.domain.ports.spi.NotePersistencePort;
import com.lando.notes_hexagonal_architecture_java.notes.domain.service.NoteService;
import com.lando.notes_hexagonal_architecture_java.notes.infrastructure.adapters.NoteJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoteConfig {

    @Bean
    public NotePersistencePort notePersistencePort(){
        return new NoteJpaAdapter();
    }

    @Bean
    public NoteServicePort noteService(){
        return new NoteService(notePersistencePort());
    }
}