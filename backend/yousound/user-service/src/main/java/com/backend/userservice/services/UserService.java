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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
       return userRepository.findById(id).orElseThrow(() ->
               new ResourceNotFoundException("User does not exist with id: " + id));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("User does not exist with username: " + username));
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
