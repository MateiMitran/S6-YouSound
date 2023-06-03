package com.backend.musicservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "playlists")
public class Playlist extends Content {

    @Min(1)
    @Column(name="nr_of_songs")
    private int nr_of_songs;

    @NotNull
    @Column(name="creator_id", nullable = false)
    private String creator_id;

    public Playlist(Long id, String name, String description, LocalDate created_at, String picture, String file, int duration, @NonNull String creator_id) {
        super(id, name, description, created_at, picture, file, duration);
        this.creator_id = creator_id;
    }

    public Playlist(int nr_of_songs, @NonNull String creator_id) {
        this.nr_of_songs = nr_of_songs;
        this.creator_id = creator_id;
    }

    public Playlist() {

    }

    public SearchResult toSearchResult() {
        return new SearchResult(this.getName(), this.getPicture(), "Playlist");
    }
}

