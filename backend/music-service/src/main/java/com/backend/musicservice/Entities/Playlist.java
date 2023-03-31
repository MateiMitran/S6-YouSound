package com.backend.musicservice.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "playlists")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="likes", nullable = false)
    private int likes;

    @Column(name="nr_of_songs", nullable = false)
    private int nr_of_songs;

    @Column(name="duration", nullable = false)
    private int duration;

    @Column(name="created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name="creator_id", nullable = false)
    private String creator_id;

    public Playlist(Long id,String name, String creator_id) {
        this.id = id;
        this.name = name;
        this.creator_id = creator_id;

        this.likes = 0;
        this.nr_of_songs = 0;
        this.duration = 0;
        this.created_at = LocalDateTime.now();
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getNr_of_songs() {
        return nr_of_songs;
    }

    public void setNr_of_songs(int nr_of_songs) {
        this.nr_of_songs = nr_of_songs;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return likes == playlist.likes && nr_of_songs == playlist.nr_of_songs && duration == playlist.duration && Objects.equals(id, playlist.id) && Objects.equals(name, playlist.name) && Objects.equals(created_at, playlist.created_at) && Objects.equals(creator_id, playlist.creator_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, likes, nr_of_songs, duration, created_at, creator_id);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", likes=" + likes +
                ", nr_of_songs=" + nr_of_songs +
                ", duration=" + duration +
                ", created_at=" + created_at +
                ", creator_id='" + creator_id + '\'' +
                '}';
    }
}

