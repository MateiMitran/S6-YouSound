package com.backend.socialservice.entities;


import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "posts")
@Data
public class Post {

    @Id
    private String id;
    @NonNull
    private String community_id;

    @NonNull
    private String user_id;

    @NonNull
    private String content;

    private LocalDateTime date;

    private int likes;

    @DBRef
    private List<Comment> comments;

    private int reach;

    public Post(String id, @NonNull String community_id, @NonNull String user_id, @NonNull String content) {
        this.id = id;
        this.community_id = community_id;
        this.user_id = user_id;
        this.content = content;
        this.date = LocalDateTime.now();
        this.likes = 0;
        this.comments = new ArrayList<>();
        this.reach = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NonNull String getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(@NonNull String community_id) {
        this.community_id = community_id;
    }

    public @NonNull String getUser_id() {
        return user_id;
    }

    public void setUser_id(@NonNull String user_id) {
        this.user_id = user_id;
    }

    public @NonNull String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getReach() {
        return reach;
    }

    public void setReach(int reach) {
        this.reach = reach;
    }
}
