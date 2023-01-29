package com.lando.notes_hexagonal_architecture_java.user.infrastructure.adapters;

import com.lando.notes_hexagonal_architecture_java.user.domain.dto.LoginDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.dto.UserDTO;
import com.lando.notes_hexagonal_architecture_java.user.domain.ports.spi.UserPersistencePort;
import com.lando.notes_hexagonal_architecture_java.user.infrastructure.entity.User;
import com.lando.notes_hexagonal_architecture_java.user.infrastructure.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserJpaAdapter implements UserPersistencePort
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {

        return userRepository.findAll();
    }

    @Override
    public User login(LoginDTO loginDTO) {

        User user = userRepository.findUserByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());

        System.out.println("Email: " + loginDTO.getEmail());
        System.out.println("Password: " + loginDTO.getPassword());

        if(user == null)
            throw new EntityNotFoundException("Entity not found");

        return user;
    }

    @Override
    public User addUser(UserDTO userDTO) {

        User user = new ModelMapper().map(userDTO, User.class);

        return userRepository.save(user);
    }
}