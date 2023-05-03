package com.backend.musicservice.repositories;

import com.backend.musicservice.entities.Album;
import com.backend.musicservice.entities.SearchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Optional<Album> findById(Long aLong);
    @Query("SELECT new com.backend.musicservice.entities.SearchResult(s.name,s.picture, 'Song') FROM Album s WHERE s.name LIKE %:query%")
    List<SearchResult> search(@Param("query") String query);
}
