package com.tomasbozzo.spring.springbootpreview.service;

import com.tomasbozzo.spring.springbootpreview.repository.model.UserEntity;

import java.util.stream.Stream;

public interface UserService {
    Stream<UserEntity> findAll();
    void insert(UserEntity entity);
    void delete(Long id);
}
