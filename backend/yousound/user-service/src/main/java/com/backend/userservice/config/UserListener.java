package com.backend.userservice.config;

import com.backend.userservice.entities.Artist;
import com.backend.userservice.entities.SignupDTO;
import com.backend.userservice.repositories.ArtistRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserListener {


    @Autowired
    private ArtistRepository artistRepository;

    @RabbitListener(queues = "user-service-queue")
    public void receiveMessage(String message) {
        // Deserialize the message to SignupDTO object
        ObjectMapper objectMapper = new ObjectMapper();
        SignupDTO signupDTO;
        try {
            signupDTO = objectMapper.readValue(message, SignupDTO.class);
        } catch (JsonProcessingException e) {
            // Handle the exception as needed
            return;
        }

        // Construct the Artist object using the SignupDTO fields
        Artist artist = new Artist();
        artist.setUsername(signupDTO.getUsername());
        artist.setPassword(signupDTO.getPassword());
        artist.setEmail(signupDTO.getEmail());
        artist.setFirstName(signupDTO.getFirstName());
        artist.setLastName(signupDTO.getLastName());

        artistRepository.save(artist);

        // Confirm successful processing
        System.out.println("Received and processed message: " + message);
    }
}