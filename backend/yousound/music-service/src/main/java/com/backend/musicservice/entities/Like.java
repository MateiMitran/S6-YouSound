package com.backend.musicservice.entities;


import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Table(name = "likes")
@Entity
@RequiredArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "song_id", nullable = false)
    private Long song_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Long getSong_id() {
        return song_id;
    }

    public void setSong_id(Long song_id) {
        this.song_id = song_id;
    }
}
