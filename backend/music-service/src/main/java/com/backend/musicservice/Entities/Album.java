package com.backend.musicservice.Entities;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name= "albums")
@NoArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="released_on", nullable = false)
    private LocalDateTime released_on;

    @Column(name="nr_of_songs", nullable = false)
    private int nr_of_songs;

    @Column(name="duration", nullable = false)
    private int duration;

    @Column(name="artist_id", nullable = false)
    private String artist_id;

    public Album(Long id, String name, LocalDateTime released_on, int nr_of_songs, int duration, String artist_id) {
        this.id = id;
        this.name = name;
        this.released_on = released_on;
        this.nr_of_songs = nr_of_songs;
        this.duration = duration;
        this.artist_id = artist_id;
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

    public LocalDateTime getReleased_on() {
        return released_on;
    }

    public void setReleased_on(LocalDateTime released_on) {
        this.released_on = released_on;
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

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return nr_of_songs == album.nr_of_songs && duration == album.duration && Objects.equals(id, album.id) && Objects.equals(name, album.name) && Objects.equals(released_on, album.released_on);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, released_on, nr_of_songs, duration);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", released_on=" + released_on +
                ", nr_of_songs=" + nr_of_songs +
                ", duration=" + duration +
                '}';
    }
}
