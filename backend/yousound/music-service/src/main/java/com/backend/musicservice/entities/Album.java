package com.backend.musicservice.entities;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name= "albums")
public class Album extends Content{

    @Column(name="nr_of_songs", nullable = false)
    private int nr_of_songs;

    @Column(name="artist_id", nullable = false)
    private String artist_id;

    public Album(Long id, String name, String description, LocalDateTime created_at, String picture, String file, int duration, int nr_of_songs, String artist_id) {
        super(id, name, description, created_at, picture, file, duration);
        this.nr_of_songs = nr_of_songs;
        this.artist_id = artist_id;
    }

    public Album(int nr_of_songs, String artist_id) {
        this.nr_of_songs = nr_of_songs;
        this.artist_id = artist_id;
    }

    public Album() {

    }

    public int getNr_of_songs() {
        return nr_of_songs;
    }

    public void setNr_of_songs(int nr_of_songs) {
        this.nr_of_songs = nr_of_songs;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public SearchResult toSearchResult() {
        return new SearchResult(this.getName(), this.getPicture(), "Album");
    }
}
