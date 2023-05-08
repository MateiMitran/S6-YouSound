package com.backend.socialservice.repositories;

import com.backend.socialservice.entities.Community;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CommunityRepository extends MongoRepository<Community, String> {

    Optional<Community> findById(String id);
}
