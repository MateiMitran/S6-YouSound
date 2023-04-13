package com.backend.musicservice.Controllers;


import com.backend.musicservice.entities.Album;
import com.backend.musicservice.Services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @GetMapping("/{id}")
    public ResponseEntity<Album> findAlbumById(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.getAlbumById(id));
    }

    @GetMapping
    public ResponseEntity<List<Album>> findAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @PostMapping("/create")
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        return ResponseEntity.ok(albumService.createAlbum(album));
    }

    @GetMapping("/by/{a_id}")
    public ResponseEntity<List<Album>> getAllAlbumsByArtist(@PathVariable String a_id) {
        return ResponseEntity.ok(albumService.getAlbumsByArtistId(a_id));
    }
}
