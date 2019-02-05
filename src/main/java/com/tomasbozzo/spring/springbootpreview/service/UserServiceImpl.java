package com.tomasbozzo.spring.springbootpreview.service;

import com.tomasbozzo.spring.springbootpreview.repository.UserRepository;
import com.tomasbozzo.spring.springbootpreview.repository.model.UserEntity;
import org.springframework.stereotype.Service;

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
    public void insert(UserEntity entity) {
        repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
