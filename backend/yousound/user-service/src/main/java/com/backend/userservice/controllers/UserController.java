package com.backend.userservice.controllers;

import com.backend.userservice.entities.User;
import com.backend.userservice.services.UserService;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanoutExchange;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Boolean> deleteByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.deleteUserByUsername(username));
    }


    @DeleteMapping("/delete-all/{userId}")
    public ResponseEntity<?> deleteAllData(@PathVariable String userId) {
        System.out.println((String) rabbitTemplate.convertSendAndReceive(fanoutExchange.getName(),"", userId));
        return ResponseEntity.ok(true);
    }





}
