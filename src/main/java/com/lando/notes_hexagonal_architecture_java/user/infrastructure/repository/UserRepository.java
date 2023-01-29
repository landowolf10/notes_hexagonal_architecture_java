package com.lando.notes_hexagonal_architecture_java.user.infrastructure.repository;

import com.lando.notes_hexagonal_architecture_java.user.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findUserByEmailAndPassword(String email, String password);
}