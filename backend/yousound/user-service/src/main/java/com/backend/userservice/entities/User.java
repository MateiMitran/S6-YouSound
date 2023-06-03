package com.backend.userservice.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Getter
@Setter
public class User {
    @Id
    @Size(min = 2, max = 36)
    private String id;

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

    public User (@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    public User(@NonNull String username, @NonNull String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {

    }



}