package com.backend.socialservice.repositories;

import com.backend.socialservice.entities.Reel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReelRepository extends MongoRepository<Reel, String> {
    Optional<Reel> findById();
}
