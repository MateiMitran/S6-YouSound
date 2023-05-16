package com.backend.musicservice.controllers;

import com.backend.musicservice.entities.Song;
import com.backend.musicservice.services.SongService;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/music/songs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SongController {


    private static final String BUCKET_NAME = "yousound-bucket";

    @Autowired
    private SongService songService;
    @JsonIgnore
    private final Storage storage;

    public SongController() throws IOException {
        Resource resource = new ClassPathResource("flash-surge-386910-252cca9aeb33.json");
        InputStream keyFileStream = resource.getInputStream();
        this.storage = StorageOptions.newBuilder()
                .setProjectId("flash-surge-386910")
                .setCredentials(ServiceAccountCredentials.fromStream(keyFileStream))
                .build()
                .getService();
    }



    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Song> createSong(@RequestBody Song song)  {
        return ResponseEntity.ok(songService.createSong(song));
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<Boolean> updateSong(@PathVariable Long id, @RequestPart MultipartFile songFile, @RequestPart MultipartFile picture) throws IOException {
        Song song = songService.getSongById(id);
        String fileName = song.getId() + "-" + songFile.getOriginalFilename();
        String pictureName = song.getId() + "-picture-"+ picture.getOriginalFilename();

        String urlSong = String.format("https://storage.googleapis.com/%s/%s", BUCKET_NAME, fileName);

        String urlPic = String.format("https://storage.googleapis.com/%s/%s", BUCKET_NAME, pictureName);

        song.setFile(urlSong);
        song.setPicture(urlPic);
        songService.createSong(song);

        storage.create(BlobInfo.newBuilder(BUCKET_NAME, pictureName)
                        .setContentType("image/jpeg")
                        .build(),
                picture.getBytes());


        storage.create(BlobInfo.newBuilder(BUCKET_NAME, fileName)
                        .setContentType("audio/mpeg")
                        .build(),
                songFile.getBytes());

        return ResponseEntity.ok(true);
    }




    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteSongById(@PathVariable Long id) {
        return ResponseEntity.ok(songService.deleteSongById(id));
    }

}
