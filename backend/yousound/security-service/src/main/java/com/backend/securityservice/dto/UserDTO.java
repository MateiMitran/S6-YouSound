package com.backend.securityservice.dto;

import com.backend.securityservice.document.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@JsonSerialize
@JsonDeserialize
public class UserDTO implements Serializable {
    private String id;
    private String username;

    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();

    }

    public UserDTO(String id, String username) {
        this.id = id;
        this.username = username;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}