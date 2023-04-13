package com.backend.musicservice.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;
    @Column(name="picture")
    private String picture;

    @Column(name="genre", nullable = false)
    private String genre;
    @Column(name="album_id")
    private Long album_id;
    @Column(name="artist_id", nullable = false)
    private String artist_id;
    @Column(name="released_on")
    private LocalDateTime released_on;
    @Column(name="plays")
    private int plays;
    @Column(name="duration")
    private String duration;

    public Song() {

    }

    public Song(Long id, String name, String genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }

    public Song(Long id, String name, String picture, String genre, Long album_id, String artist_id, LocalDateTime released_on, String duration) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.genre = genre;
        this.album_id = album_id;
        this.artist_id = artist_id;
        this.released_on = released_on;
        this.plays = 0;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public LocalDateTime getReleased_on() {
        return released_on;
    }

    public void setReleased_on(LocalDateTime released_on) {
        this.released_on = released_on;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return plays == song.plays && Objects.equals(id, song.id) && Objects.equals(name, song.name) && Objects.equals(picture, song.picture) && Objects.equals(genre, song.genre) && Objects.equals(album_id, song.album_id) && Objects.equals(artist_id, song.artist_id) && Objects.equals(released_on, song.released_on) && Objects.equals(duration, song.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, picture, genre, album_id, artist_id, released_on, plays, duration);
    }
}
