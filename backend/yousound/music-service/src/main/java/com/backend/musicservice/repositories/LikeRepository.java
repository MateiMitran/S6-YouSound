package com.backend.musicservice.repositories;

import com.backend.musicservice.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {


    Optional<Like> findById(Long id);

    @Query(value = "SELECT COUNT(*) FROM your_table_name WHERE song_id = :song_id", nativeQuery = true)
    int countBySongId(@Param("song_id") String song_id);
}
