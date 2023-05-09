package com.backend.socialservice.services;


import com.backend.socialservice.entities.Comment;
import com.backend.socialservice.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsFromPostWithId(String id) {
        List<Comment> allComments = commentRepository.findAll();
        List<Comment> commentsFromPost = new ArrayList<>();
        for(Comment c: allComments) {
            if (c.getPost_id().equals(id)) {
                commentsFromPost.add(c);
            }
        }
        return commentsFromPost;
    }
    public boolean deleteComment(String id) {
        commentRepository.deleteById(id);
        return true;
    }

    public boolean likeComment(String id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            Comment c = comment.get();
            c.setLikes(c.getLikes()+1);
            return true;
        }
        return false;
    }

    public boolean unlikeComment(String id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            Comment c = comment.get();
            c.setLikes(c.getLikes()-1);
            return true;
        }
        return false;
    }

    public List<Comment> getRepliesFromComment(String id) {
        if (commentRepository.findById(id).isPresent()) {
            return commentRepository.findById(id).get().getReplies();
        }
        return null;
    }

}