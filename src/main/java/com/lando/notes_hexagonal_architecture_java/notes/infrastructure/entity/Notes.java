package com.lando.notes_hexagonal_architecture_java.notes.infrastructure.entity;

import com.lando.notes_hexagonal_architecture_java.user.infrastructure.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noteid")
    private Integer noteId;
    @Column(name = "userid")
    private Integer userId;
    private String owner;
    private String title;
    private String content;
}