package com.backend.musicservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="songs")
@Getter
@Setter
public class Song extends Content{

    @NotNull
    @Size(min=1, max=50)
    @Column(name="genre", nullable = false)
    private String genre;
    @Column(name="album_id")
    private Long album_id;
    @NotNull
    @Column(name="artist_id", nullable = false)
    private String artist_id;
    @Min(0)
    @Column(name="plays")
    private int plays;


    public Song(Long id, String name, String description, String picture, LocalDate created_at, String file, int duration, @NonNull String genre, Long album_id, @NonNull String artist_id) {
        super(id, name, description, created_at, picture,  file, duration);
        this.genre = genre;
        this.album_id = album_id;
        this.artist_id = artist_id;
        this.plays = 0;
    }

    public Song(Long id, String name, String description, LocalDate created_at, String picture, String file, int duration, @NonNull String genre, @NonNull String artist_id) {
        super(id, name,description, created_at, picture, file, duration);
        this.artist_id = artist_id;
        this.genre = genre;
        this.plays = 0;
    }


    public Song() {
        this.plays = 0;
    }


    public SearchResult toSearchResult() {
        return new SearchResult(this.getName(), this.getPicture(), "Song");
    }
}
