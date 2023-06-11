package com.backend.musicservice.services;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqReceiver {


    @Autowired
    private SongService songService;

    @RabbitListener(queues = "music-queue")
    public void receive(String message) {
        songService.deleteLikedSongsOfUser(message);
        System.out.println("Music Service received: " + message);
    }
}
