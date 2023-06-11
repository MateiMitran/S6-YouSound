package com.backend.musicservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;


@MappedSuperclass
@Getter
@Setter
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private String picture;

    @NotNull
    @Column(nullable = false)
    private String file;

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private LocalDate created_at;

    @Min(0)
    @Column(nullable = false)
    private int likes;

    @Min(0)
    @Column(nullable = false)
    private int duration;

    public Content(Long id, @NonNull String name, @NonNull String description, @NonNull LocalDate created_at,
                   @NonNull String picture, @NonNull String file, int duration) {
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
