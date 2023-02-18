package com.lando.notes_hexagonal_architecture_java.user.infrastructure.repository;

import com.lando.notes_hexagonal_architecture_java.user.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findUserByEmailAndPassword(String email, String password);
    Optional<User> findUserByEmail(String email);
}