package com.lando.notes_hexagonal_architecture_java.notes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userID;
    private String owner;
    private String title;
    private String content;

    public Integer getId() {
        return id;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}