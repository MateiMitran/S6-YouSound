package com.backend.socialservice.entities;


import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "posts")
@Data
@Getter
@Setter
public class Post {

    @Id
    private String id;
    @NonNull
    private String community_id;

    @NonNull
    private String user_id;

    @NonNull
    @Size(min = 1, max = 255)
    private String content;

    @PastOrPresent
    private LocalDateTime date;

    @Min(0)
    private int likes;

    @DBRef
    private List<Comment> comments;

    @Min(0)
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


}
