package com.backend.musicservice.Repositories;

import com.backend.musicservice.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findById(Long aLong);
}
