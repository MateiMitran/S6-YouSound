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

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}