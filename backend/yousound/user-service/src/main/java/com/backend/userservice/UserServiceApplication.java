package com.backend.userservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.backend.userservice.controllers", "com.backend.userservice.services", "com.backend.userservice.config", "com.backend.userservice.entities", "com.backend.userservice.repositories"})
@EnableMongoRepositories(basePackages = "com.backend.userservice.repositories")
@EntityScan(basePackages = "com.backend.userservice.entities")
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}