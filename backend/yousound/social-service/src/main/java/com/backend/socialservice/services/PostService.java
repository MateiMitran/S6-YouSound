package com.backend.socialservice.services;


import com.backend.socialservice.entities.Post;
import com.backend.socialservice.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getPostsFromCommunity(String community_id) {
        List<Post> allPosts = postRepository.findAll();
        List<Post> communityPosts = new ArrayList<>();
        for (Post p: allPosts) {
            if (p.getCommunity_id().equals(community_id)) {
                communityPosts.add(p);
            }
        }
        return communityPosts;
    }

    public boolean deletePost(Post post) {
        postRepository.delete(post);
        return true;
    }

    public Post getPostById(String id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public boolean likePost(String id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            post.get().setLikes(post.get().getLikes()+1);
            postRepository.save(post.get());
            return true;
        }
        return false;
    }

    public void deleteByUserId(String userId) {
        List<Post> posts = postRepository.findAll();
        for (Post post: posts) {
            if (post.getUser_id().equals(userId)) {
                postRepository.delete(post);
            }
        }
    }

    public boolean unlikePost(String id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            post.get().setLikes(post.get().getLikes()-1);
            postRepository.save(post.get());
            return true;
        }
        return false;
    }



}
