package com.backend.socialservice.entities;


import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class Comment {

    @Id
    private String id;
    private String content;

    private String post_id;
    private String user_id;
    private int likes;
    private List<Comment> replies;

    public Comment(String id, String content, String post_id, String user_id) {
        this.id = id;
        this.content = content;
        this.post_id = post_id;
        this.user_id = user_id;
        this.likes = 0;
        this.replies = new ArrayList<>();
    }

    public Comment() {
        this.likes = 0;
        this.replies = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }
}
