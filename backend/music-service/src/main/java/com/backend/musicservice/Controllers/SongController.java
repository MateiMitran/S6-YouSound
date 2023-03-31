package com.backend.musicservice.Controllers;

import com.backend.musicservice.Entities.Song;
import com.backend.musicservice.Services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        return ResponseEntity.ok(songService.createSong(song));
    }

    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }
}
