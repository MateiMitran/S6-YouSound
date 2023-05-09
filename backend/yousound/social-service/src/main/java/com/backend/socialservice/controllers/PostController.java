package com.backend.socialservice.controllers;


import com.backend.socialservice.entities.Post;
import com.backend.socialservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @GetMapping("/from-community/{community_id}")
    public ResponseEntity<List<Post>> getPostsFromCommunity(@PathVariable String community_id) {
        return ResponseEntity.ok(postService.getPostsFromCommunity(community_id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    //@PostMapping("/like/{id{")



}
