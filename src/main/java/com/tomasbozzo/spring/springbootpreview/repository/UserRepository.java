package com.tomasbozzo.spring.springbootpreview.repository;

import com.tomasbozzo.spring.springbootpreview.repository.model.UserEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface UserRepository extends Repository<UserEntity, Long> {
    Stream<UserEntity> findAll();
    Optional<UserEntity> findById(Long id);
    UserEntity save(UserEntity entity);
    void deleteById(Long var1);
}
