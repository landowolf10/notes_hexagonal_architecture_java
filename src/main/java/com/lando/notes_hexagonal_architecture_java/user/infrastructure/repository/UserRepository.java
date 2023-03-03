package com.lando.notes_hexagonal_architecture_java.user.infrastructure.repository;

import com.lando.notes_hexagonal_architecture_java.user.infrastructure.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer>
{
    Users findUserByEmailAndPassword(String email, String password);
    Optional<Users> findUserByEmail(String email);
}