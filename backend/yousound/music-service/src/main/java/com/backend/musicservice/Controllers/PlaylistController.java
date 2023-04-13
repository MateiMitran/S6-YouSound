package com.backend.musicservice.Controllers;


import com.backend.musicservice.entities.Playlist;
import com.backend.musicservice.Services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/playlists")
public class PlaylistController {
    @Autowired
    PlaylistService playlistService;

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.getPlaylistById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        return ResponseEntity.ok(playlistService.createPlaylist(playlist));
    }

    @GetMapping()
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        return ResponseEntity.ok(playlistService.getAllPlaylists());
    }

    @GetMapping("/user/{u_id}")
    public ResponseEntity<List<Playlist>> getAllFromUserWithId(@PathVariable String u_id) {
        return ResponseEntity.ok(playlistService.getPlaylistsOfUser(u_id));
    }
}
