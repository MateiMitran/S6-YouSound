package com.backend.musicservice.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="songs")
public class Song extends Content{

    @Column(name="genre", nullable = false)
    private String genre;
    @Column(name="album_id")
    private Long album_id;
    @Column(name="artist_id", nullable = false)
    private String artist_id;
    @Column(name="plays")
    private int plays;


    public Song(Long id, String name, String description, String picture, LocalDate created_at, String file, int duration, String genre, Long album_id, String artist_id) {
        super(id, name, description, created_at, picture,  file, duration);
        this.genre = genre;
        this.album_id = album_id;
        this.artist_id = artist_id;
        this.plays = 0;
    }

    public Song(Long id, String name, String description, LocalDate created_at, String picture, String file, int duration, String genre, String artist_id) {
        super(id, name,description, created_at, picture, file, duration);
        this.artist_id = artist_id;
        this.genre = genre;
        this.plays = 0;
    }


    public Song() {
        this.plays = 0;
    }




    public Long getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Long album_id) {
        this.album_id = album_id;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public SearchResult toSearchResult() {
        return new SearchResult(this.getName(), this.getPicture(), "Song");
    }
}
