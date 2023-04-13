package com.backend.userservice.services;


import com.backend.userservice.entities.User;
import com.backend.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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
}
