package com.backend.songservice.Repositories;

import com.backend.songservice.Entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findById(Long id);
}
