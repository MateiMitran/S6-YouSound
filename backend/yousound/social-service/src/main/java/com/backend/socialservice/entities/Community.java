package com.backend.socialservice.entities;

import com.backend.userservice.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.validation.constraints.*;

@Document(collection="communities")
@Data
@Getter
@Setter
public class Community {


    @Id
    private String id;

    @NonNull
    private String artist_id;

    @NonNull
    @Size(min = 1, max = 255)
    private String description;

    @DBRef
    private List<User> users;

    public Community(String id, @NonNull String artist_id, @NonNull String description) {
        this.id = id;
        this.artist_id = artist_id;
        this.description = description;
        this.users = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Community community = (Community) o;
        return Objects.equals(id, community.id) && artist_id.equals(community.artist_id) && description.equals(community.description) && Objects.equals(users, community.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artist_id, description, users);
    }
}
