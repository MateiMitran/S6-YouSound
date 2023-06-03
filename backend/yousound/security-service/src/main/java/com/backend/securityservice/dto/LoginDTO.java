package com.backend.securityservice.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginDTO {
    @NonNull
    @Size(min=2, max=30)
    private String username;

    @NonNull
    @Size(min=4, max=100)
    private String password;

}