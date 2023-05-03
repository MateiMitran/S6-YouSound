package com.backend.userservice.controllers;

import com.backend.userservice.entities.User;
import com.backend.userservice.services.UserService;
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

    @PutMapping("/role/{id}")
    public ResponseEntity<User> updateUserRole(@PathVariable String id, @RequestBody String role) {
        return ResponseEntity.ok(userService.updateUserRole(id, role));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        return ResponseEntity.ok(userService.login(user.getUsername(), user.getPassword()));
    }

    @PostMapping("/verify/{token}")
    public ResponseEntity<User> verify(@PathVariable String token) {
        return ResponseEntity.ok(userService.verifyByToken(token));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Boolean> deleteByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.deleteUserByUsername(username));
    }


}
