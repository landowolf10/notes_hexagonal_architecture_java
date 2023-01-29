package com.lando.notes_hexagonal_architecture_java.notes.infrastructure.repository;

import com.lando.notes_hexagonal_architecture_java.notes.infrastructure.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Notes, Integer>
{
    Notes findNoteById(int id);
    List<Notes> findNotesByUserID(@Param("userID") int userID);
}