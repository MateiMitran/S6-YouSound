package com.backend.socialservice.entities;

import com.backend.userservice.entities.User;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Document(collection="communities")
@Data
public class Community {


    @Id
    private String id;

    @NonNull
    private String artist_id;

    @NonNull
    private String description;

    @DBRef
    private List<User> users;

    public Community(String id, String artist_id, String description) {
        this.id = id;
        this.artist_id = artist_id;
        this.description = description;
        this.users = new ArrayList<>();
    }

    public Community(){
        this.users = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUser_ids() {
        return users;
    }

    public void setUser_ids(List<User> users) {
        this.users = users;
    }

    public void addUserId(User user) {
        this.users.add(user);
    }

    public void removeUserId(User user) {
        this.users.remove(user);
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
