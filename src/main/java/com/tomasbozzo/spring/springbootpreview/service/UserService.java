package com.tomasbozzo.spring.springbootpreview.service;

import com.tomasbozzo.spring.springbootpreview.repository.model.UserEntity;

import java.util.Optional;
import java.util.stream.Stream;

public interface UserService {
    Stream<UserEntity> findAll();
    UserEntity insert(UserEntity entity);
    void delete(Long id);
    Optional<UserEntity> findById(Long id);
}
