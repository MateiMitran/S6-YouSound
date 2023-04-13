package com.backend.userservice.repositories;

import com.backend.userservice.entities.Community;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CommunityRepository extends MongoRepository<Community, String> {

    Optional<Community> findById(String id);
}
