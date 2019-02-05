package com.tomasbozzo.spring.springbootpreview.web.controller;

import com.tomasbozzo.spring.springbootpreview.repository.model.UserEntity;
import com.tomasbozzo.spring.springbootpreview.service.UserService;
import com.tomasbozzo.spring.springbootpreview.web.ResourceNotFoundException;
import com.tomasbozzo.spring.springbootpreview.web.model.UserResource;
import lombok.SneakyThrows;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Resources<UserResource> findAll() {
        List<UserResource> users = userService.findAll()
                .map(this::toUserResource)
                .collect(Collectors.toList());

        return new Resources<>(users, linkTo(methodOn(UserController.class).findAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    public HttpEntity<UserResource> findById(@PathVariable Long id) throws ResourceNotFoundException {
        UserResource user = userService.findById(id)
                .map(this::toUserResource)
                .orElseThrow(() -> new ResourceNotFoundException("User " + id + " was not found"));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @SneakyThrows
    public HttpEntity<Void> create(@RequestBody UserResource user) {
        UserEntity userEntity = userService.insert(toUserEntity(user));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", linkTo(methodOn(UserController.class).findById(userEntity.getId())).toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private UserEntity toUserEntity(UserResource user) {
        return UserEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    @SneakyThrows
    private UserResource toUserResource(UserEntity user) {
        UserResource result = UserResource.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();

        result.add(linkTo(methodOn(UserController.class).delete(user.getId())).withRel("delete"));
        result.add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel());

        return result;
    }
}
