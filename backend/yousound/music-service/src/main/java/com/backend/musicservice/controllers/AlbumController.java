package com.backend.musicservice.controllers;


import com.backend.musicservice.entities.Album;
import com.backend.musicservice.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/music/albums")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public ResponseEntity<Album> createAlbum(@Validated @RequestBody Album album) {
        return ResponseEntity.ok(albumService.createAlbum(album));
    }

    @GetMapping("/by/{aId}")
    public ResponseEntity<List<Album>> getAllAlbumsByArtist(@PathVariable String aId) {
        return ResponseEntity.ok(albumService.getAlbumsByArtistId(aId));
    }
}
