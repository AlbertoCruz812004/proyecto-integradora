package com.allstock.app.services;

import com.allstock.app.persistence.entities.UserEntity;
import com.allstock.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements CrudService<UserEntity, Long>, UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity save(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public void update(UserEntity user) {
        Optional<UserEntity> optionalUser = repository.findById(user.getId());
        optionalUser.ifPresent(repository::save);

    }

    @Override
    public boolean delete(UserEntity user) {
        Optional<UserEntity> optionalUser = repository.findById(user.getId());
        optionalUser.ifPresent(repository::delete);
        return repository.existsById(user.getId());
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<UserEntity> findAllById() {
        return repository.findAll();
    }

    @Override
    public boolean findUserByUsername(String username, String password) {

        return false;
    }
}
