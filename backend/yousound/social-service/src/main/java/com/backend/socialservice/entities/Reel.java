package com.backend.socialservice.entities;


import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="reels")
@Data
public class Reel {


    @NonNull
    private String community_id;

    @NonNull
    private String user_id;

    @NonNull
    private String content;

    private LocalDateTime date;

    private int likes;

    private int reach;

    public Reel(@NonNull String community_id, @NonNull String user_id, @NonNull String content) {
        this.community_id = community_id;
        this.user_id = user_id;
        this.content = content;
        this.date = LocalDateTime.now();
        this.likes = 0;
        this.reach = 0;
    }

    public Reel() {
        this.date = LocalDateTime.now();
        this.likes = 0;
        this.reach = 0;
    }

    public String getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(String community_id) {
        this.community_id = community_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getReach() {
        return reach;
    }

    public void setReach(int reach) {
        this.reach = reach;
    }
}
