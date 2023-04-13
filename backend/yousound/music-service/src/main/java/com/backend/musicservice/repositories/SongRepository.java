package com.backend.musicservice.repositories;

import com.backend.musicservice.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findById(Long id);
}
