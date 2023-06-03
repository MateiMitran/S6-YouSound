package com.backend.userservice.repositories;

import com.backend.userservice.entities.Artist;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArtistRepository extends MongoRepository<Artist, String> {

    Optional<Artist> findById(@NonNull String id);
}
