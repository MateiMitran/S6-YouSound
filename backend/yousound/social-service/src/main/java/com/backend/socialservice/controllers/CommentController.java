package com.backend.socialservice.controllers;


import com.backend.socialservice.entities.Comment;
import com.backend.socialservice.services.CommentService;
import com.google.cloud.language.v1.Sentiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping()
    public ResponseEntity<List<Comment>> getComments() {
        return ResponseEntity.ok(commentService.getComments());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createComment(@Validated @RequestBody Comment comment) {

        Sentiment sentiment = commentService.analyzeTextSentiment(comment.getContent());
        if (sentiment.getScore()<0) {
            return ResponseEntity.badRequest().body("Comment is not allowed on the platform");
        }
        return ResponseEntity.ok(commentService.createComment(comment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @GetMapping("/from-post/{id}")
    public ResponseEntity<List<Comment>> getCommentsFromPostWithId(@PathVariable String id) {
        return ResponseEntity.ok(commentService.getCommentsFromPostWithId(id));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable String id) {
        return ResponseEntity.ok(commentService.deleteComment(id));
    }

    @GetMapping("/{id}/get-replies")
    public ResponseEntity<List<Comment>> getRepliesFromCommentWithId(@PathVariable String id) {
        return ResponseEntity.ok(commentService.getRepliesFromComment(id));
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<Boolean> likeComment(@PathVariable String id) {
        return ResponseEntity.ok(commentService.likeComment(id));
    }

    @PostMapping("/unlike/{id}")
    public ResponseEntity<Boolean> unlikeComment(@PathVariable String id) {
        return ResponseEntity.ok(commentService.unlikeComment(id));
    }

}
