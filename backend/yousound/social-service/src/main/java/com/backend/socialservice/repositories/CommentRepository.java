package com.backend.socialservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.backend.socialservice.entities.Comment;

import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {
    Optional<Comment> findById();
}
