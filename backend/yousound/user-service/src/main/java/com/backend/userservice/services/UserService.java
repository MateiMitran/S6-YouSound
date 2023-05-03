package com.backend.userservice.services;


import com.backend.userservice.entities.User;
import com.backend.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired(required = false)
    EmailService emailService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUserRole(String id, String role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + id));
        user.setType(role);
        return userRepository.save(user);
    }

    public User getUserById(String id) {
       return userRepository.findById(id).orElseThrow(() ->
               new ResourceNotFoundException("User does not exist with id: " + id));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("User does not exist with username: " + username));
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("User does not exist with username: " + username));
        if (user.getPassword().equals(password) && user.isVerified()) {
            return user;
        } else {
            throw new ResourceNotFoundException("Incorrect password");
        }
    }

    public User register(User user) {
        String token = UUID.randomUUID().toString();
        user.setVerification_token(token);
        emailService.sendVerificationEmail(user);
        return userRepository.save(user);
    }

    public User verifyByToken(String verification_token) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getVerification_token().equals(verification_token)) {
                user.setVerified(true);
                return userRepository.save(user);
            }
        }
        return null;
    }

    public boolean deleteUserById(String id) {

        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUserByUsername(String username) {

        if (userRepository.findByUsername(username).isPresent()) {
            userRepository.deleteById(username);
            return true;
        } else {
            return false;
        }
    }

}
