package com.backend.socialservice.entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
@Getter
@Setter
public class Comment {

    @Id
    private String id;

    @Size(min = 1, max = 255)
    private String content;
    private String post_id;
    private String user_id;
    @Min(0)
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

}
