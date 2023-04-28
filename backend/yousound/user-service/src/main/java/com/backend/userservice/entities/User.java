package com.backend.userservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    private String id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String verification_token;

    private boolean verified;

    private String type;

    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(@NonNull String username, @NonNull String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User(@NonNull String username, @NonNull String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.verified = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getVerification_token() {
        return verification_token;
    }

    public void setVerification_token(String verification_token) {
        this.verification_token = verification_token;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}