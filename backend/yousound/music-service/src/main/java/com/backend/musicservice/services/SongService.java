package com.backend.musicservice.services;

import com.backend.musicservice.entities.Song;
import com.backend.musicservice.repositories.SongRepository;
import com.google.cloud.storage.BlobInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(Long id) {
        return songRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Song createSong(Song song) {
        return songRepository.save(song);
    }


}
