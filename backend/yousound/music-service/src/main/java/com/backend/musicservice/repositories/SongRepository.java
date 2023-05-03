package com.backend.musicservice.repositories;

import com.backend.musicservice.entities.SearchResult;
import com.backend.musicservice.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findById(Long id);

    @Query("SELECT new com.backend.musicservice.entities.SearchResult(s.name,s.picture, 'Song') FROM Song s WHERE s.name LIKE %:query%")
    List<SearchResult> search(@Param("query") String query);
}
