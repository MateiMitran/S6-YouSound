package com.backend.securityservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
public class SignupDTO {

    @NonNull
    @Size(min=2, max=30)
    private String username;
    @NonNull
    @Size(min=4, max=100)
    private String password;

    @Email
    @Size(max=50)
    private String email;

    @Size(max=30)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "must contain only letters")
    private String firstName;

    @Size(max=30)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "must contain only letters")
    private String lastName;
}