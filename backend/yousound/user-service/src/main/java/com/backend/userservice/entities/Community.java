package com.backend.userservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Document(value="communities")
@Data
public class Community {


    @Id
    private String id;

    @NonNull
    private String artist_id;

    @NonNull
    private String description;

    private List<String> user_ids;

    public Community(String id, String artist_id, String description) {
        this.id = id;
        this.artist_id = artist_id;
        this.description = description;
        this.user_ids = new ArrayList<>();
    }

    public Community(){
        this.user_ids = new ArrayList<>();
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

    public List<String> getUser_ids() {
        return user_ids;
    }

    public void setUser_ids(List<String> user_ids) {
        this.user_ids = user_ids;
    }

    public void addUserId(String user_id) {
        this.user_ids.add(user_id);
    }

    public void removeUserId(String user_id) {
        this.user_ids.remove(user_id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Community community = (Community) o;
        return Objects.equals(id, community.id) && artist_id.equals(community.artist_id) && description.equals(community.description) && Objects.equals(user_ids, community.user_ids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artist_id, description, user_ids);
    }
}
