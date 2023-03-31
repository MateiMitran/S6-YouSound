package com.backend.userservice.Repositories;

import com.backend.userservice.Entities.Community;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CommunityRepository extends MongoRepository<Community, String> {

    Optional<Community> findById(String id);
}
