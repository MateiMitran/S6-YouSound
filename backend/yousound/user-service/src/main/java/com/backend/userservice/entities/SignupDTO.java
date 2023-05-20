package com.backend.userservice.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}