package com.backend.musicservice.controllers;


import com.backend.musicservice.entities.Playlist;
import com.backend.musicservice.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/music/playlists")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlaylistController {
    @Autowired
    PlaylistService playlistService;

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.getPlaylistById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Playlist> createPlaylist(@Validated @RequestBody Playlist playlist) {
        return ResponseEntity.ok(playlistService.createPlaylist(playlist));
    }

    @GetMapping()
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        return ResponseEntity.ok(playlistService.getAllPlaylists());
    }

    @GetMapping("/user/{uId}")
    public ResponseEntity<List<Playlist>> getAllFromUserWithId(@PathVariable String uId) {
        return ResponseEntity.ok(playlistService.getPlaylistsOfUser(uId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePlaylistById(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.deletePlaylistById(id));
    }
}
