package com.tomasbozzo.spring.springbootpreview.repository.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}