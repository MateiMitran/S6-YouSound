package com.backend.socialservice.repositories;

import com.backend.socialservice.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {
    Optional<Post> findById();
}
