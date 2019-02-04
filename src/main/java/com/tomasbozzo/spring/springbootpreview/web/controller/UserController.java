package com.tomasbozzo.spring.springbootpreview.web.controller;

import com.tomasbozzo.spring.springbootpreview.repository.model.UserEntity;
import com.tomasbozzo.spring.springbootpreview.service.UserService;
import com.tomasbozzo.spring.springbootpreview.web.model.UserResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll()
                .map(this::toUserResource)
                .collect(Collectors.toList()));
        return "users";
    }

    @PostMapping("/create")
    public String create(@Valid UserResource user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "users-create";
        }

        userService.insert(toUserEntity(user));
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", UserResource.builder().build());
        return "users-create";
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
