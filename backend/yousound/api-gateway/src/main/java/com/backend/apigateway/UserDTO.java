package com.backend.apigateway;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@JsonSerialize
@JsonDeserialize
@NoArgsConstructor
public class UserDTO implements Serializable {
    private String id;
    private String username;

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