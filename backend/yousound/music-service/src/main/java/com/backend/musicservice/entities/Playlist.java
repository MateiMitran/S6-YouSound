package com.backend.musicservice.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "playlists")
public class Playlist extends Content {

    @Column(name="nr_of_songs")
    private int nr_of_songs;
    @Column(name="creator_id", nullable = false)
    private String creator_id;

    public Playlist(Long id, String name, String description, LocalDateTime created_at, String picture, String file, int duration, int nr_of_songs, String creator_id) {
        super(id, name, description, created_at, picture, file, duration);
        this.nr_of_songs = nr_of_songs;
        this.creator_id = creator_id;
    }

    public Playlist(int nr_of_songs, String creator_id) {
        this.nr_of_songs = nr_of_songs;
        this.creator_id = creator_id;
    }

    public Playlist() {

    }

    public int getNr_of_songs() {
        return nr_of_songs;
    }

    public void setNr_of_songs(int nr_of_songs) {
        this.nr_of_songs = nr_of_songs;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }
}

