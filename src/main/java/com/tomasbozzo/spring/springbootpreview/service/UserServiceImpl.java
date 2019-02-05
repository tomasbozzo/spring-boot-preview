package com.tomasbozzo.spring.springbootpreview.service;

import com.tomasbozzo.spring.springbootpreview.repository.UserRepository;
import com.tomasbozzo.spring.springbootpreview.repository.model.UserEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Stream<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public UserEntity insert(UserEntity entity) {
        return repository.save(entity);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return repository.findById(id);
    }
}
