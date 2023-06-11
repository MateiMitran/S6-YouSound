package com.backend.socialservice.services;


import com.backend.socialservice.entities.Comment;
import com.backend.socialservice.repositories.CommentRepository;
import com.google.cloud.language.v1.Sentiment;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;

//    @Autowired
//    private final LanguageService languageService;

    public CommentService(CommentRepository commentRepository,@Value("${api.key}") String apiKey) throws IOException {
        this.commentRepository = commentRepository;
//        this.languageService = new LanguageService(apiKey);
    }


    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

//    public Sentiment analyzeTextSentiment(String text) {
//        return languageService.analyzeSentiment(text);
//    }

    public List<Comment> getComments() { return commentRepository.findAll(); }

    public Comment getCommentById(String id) {
        return commentRepository.findById(id).orElse(null);
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

    public void deleteByUserId(String userId) {
        List<Comment> comments = commentRepository.findAll();
        for (Comment comment: comments) {
            if (comment.getUser_id().equals(userId)) {
                commentRepository.delete(comment);
            }
        }
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

//    @PreDestroy
//    public void cleanup() {
//        languageService.close();
//    }

}