package com.backend.socialservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.backend.socialservice.controllers", "com.backend.socialservice.services", "com.backend.socialservice.config", "com.backend.socialservice.entities", "com.backend.socialservice.repositories"})
@EnableMongoRepositories(basePackages = "com.backend.socialservice.repositories")
@EntityScan(basePackages = "com.backend.socialservice.entities")
public class SocialServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialServiceApplication.class, args);
    }
}