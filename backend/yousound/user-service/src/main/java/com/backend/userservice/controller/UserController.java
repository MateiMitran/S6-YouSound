package com.backend.userservice.controller;

import com.backend.userservice.entities.User;
import com.backend.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
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

}
