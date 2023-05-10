package com.backend.musicservice.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@MappedSuperclass
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String picture;
    @Column(nullable = false)
    private String file;
    @Column(nullable = false)
    private LocalDateTime created_at;
    @Column(nullable = false)
    private int likes;
    @Column(nullable = false)
    private int duration;

    public Content(Long id, String name, String description, LocalDateTime created_at, String picture, String file, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.file = file;
        this.created_at = created_at;
        this.likes = 0;
        this.duration = duration;
    }

    public Content() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return likes == content.likes && duration == content.duration && Objects.equals(id, content.id) && Objects.equals(name, content.name) && Objects.equals(description, content.description) && Objects.equals(picture, content.picture) && Objects.equals(file, content.file) && Objects.equals(created_at, content.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, picture, file, created_at, likes, duration);
    }
}
