package com.backend.socialservice.services;

import org.checkerframework.checker.units.qual.A;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqReceiver {


    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private ReelService reelService;


    @RabbitListener(queues = "social-queue")
    public void receive(String message) {
        //message is userId
        commentService.deleteByUserId(message);
        postService.deleteByUserId(message);
        reelService.deleteByUserId(message);
        System.out.println("Social Service received: " + message);
    }
}
