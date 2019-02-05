package com.tomasbozzo.spring.springbootpreview.web.controller;

import com.tomasbozzo.spring.springbootpreview.repository.model.UserEntity;
import com.tomasbozzo.spring.springbootpreview.service.UserService;
import com.tomasbozzo.spring.springbootpreview.web.model.UserResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Stream<UserResource> findAll() {
        return userService.findAll()
                .map(this::toUserResource);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserResource user) {
        userService.insert(toUserEntity(user));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    private UserEntity toUserEntity(UserResource user) {
        return UserEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    private UserResource toUserResource(UserEntity user) {
        return UserResource.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
