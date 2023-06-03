package com.backend.musicservice.controllers;

import com.backend.musicservice.entities.Song;
import com.backend.musicservice.services.SongService;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


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
    public ResponseEntity<Song> getSongById(@PathVariable Long id) throws Exception {

        Song song = songService.getSongById(id);
        String secretKey = "*G-KaPdRgUkXp2s5v8y/B?E(H+MbQeTh";
        song.setFile(decrypt(song.getFile(), secretKey));
        song.setPicture(decrypt(song.getPicture(), secretKey));
        return ResponseEntity.ok(song);
    }

    @PostMapping("/create")
    public ResponseEntity<Song> createSong(@Validated @RequestBody Song song)  {
        return ResponseEntity.ok(songService.createSong(song));
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<Boolean> updateSong(@PathVariable Long id, @RequestPart MultipartFile songFile, @RequestPart MultipartFile picture) throws Exception {

        String secretKey = "*G-KaPdRgUkXp2s5v8y/B?E(H+MbQeTh";
        Song song = songService.getSongById(id);
        String fileName = song.getId() + "-" + songFile.getOriginalFilename();
        String pictureName = song.getId() + "-picture-"+ picture.getOriginalFilename();

        String urlSong = encrypt(String.format("https://storage.googleapis.com/%s/%s", BUCKET_NAME, fileName), secretKey);

        String urlPic = encrypt(String.format("https://storage.googleapis.com/%s/%s", BUCKET_NAME, pictureName), secretKey);

        song.setFile(urlSong);
        song.setPicture(urlPic);
        songService.createSong(song);

        byte[] songFileEncrypted = encryptFile(songFile.getBytes(), secretKey);
        byte[] pictureEncrypted = encryptFile(picture.getBytes(), secretKey);

        storage.create(BlobInfo.newBuilder(BUCKET_NAME, pictureName)
                        .setContentType("image/jpeg")
                        .build(),
                pictureEncrypted);


        storage.create(BlobInfo.newBuilder(BUCKET_NAME, fileName)
                        .setContentType("audio/mpeg")
                        .build(),
               songFileEncrypted);

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

    @GetMapping("/analytics/{id}")
    public ResponseEntity<?> getAnalytics(@PathVariable Long id) {
        return ResponseEntity.ok(songService.calculateAnalytics(id));
    }

    private byte[] encryptFile(byte[] fileData, String secretKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(fileData);
    }

    private String encrypt(String strToEncrypt, String secretKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    }

    private String decrypt(String strToDecrypt, String secretKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));
        return new String(decrypted, StandardCharsets.UTF_8);
    }

}
