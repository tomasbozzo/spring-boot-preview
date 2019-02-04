package com.tomasbozzo.spring.springbootpreview.service;

import com.tomasbozzo.spring.springbootpreview.repository.model.UserEntity;
import com.tomasbozzo.spring.springbootpreview.web.model.UserResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;

@Service
public class UserServiceImpl implements UserService {

    private List<UserEntity> users = new ArrayList<>(singletonList(UserEntity.builder()
            .id(1L)
            .firstName("Tomás")
            .lastName("Bozzo")
            .email("tomasbozzo@fakemail.com")
            .build()));

    @Override
    public Stream<UserEntity> findAll() {
        return users.stream();
    }

    @Override
    public void insert(UserEntity entity) {
        users.add(entity);
    }

    @Override
    public void delete(Long id) {
        users.removeIf(u -> u.getId().equals(id));
    }
}
