package com.backend.musicservice.Repositories;

import com.backend.musicservice.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Optional<Album> findById(Long aLong);
}
