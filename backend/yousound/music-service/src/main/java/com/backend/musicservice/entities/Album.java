package com.backend.musicservice.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name= "albums")
@Getter
@Setter
public class Album extends Content{

    @NotNull
    @Column(name="nr_of_songs", nullable = false)
    private int nr_of_songs;

    @NotNull
    @Column(name="artist_id", nullable = false)
    private String artist_id;


    public Album(Long id, String name, String description, LocalDate created_at, String picture, String file, int duration, int nr_of_songs, @NonNull String artist_id) {
        super(id, name, description, created_at, picture, file, duration);
        this.nr_of_songs = nr_of_songs;
        this.artist_id = artist_id;
    }

    public Album(int nr_of_songs, @NonNull String artist_id) {
        this.nr_of_songs = nr_of_songs;
        this.artist_id = artist_id;
    }

    public Album() {

    }

    public SearchResult toSearchResult() {
        return new SearchResult(this.getName(), this.getPicture(), "Album");
    }
}
