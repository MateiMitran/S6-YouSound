package com.backend.musicservice.Services;

import com.backend.musicservice.entities.Song;
import com.backend.musicservice.Repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(Long id) {
        return songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
    }

    public Song createSong(Song song) {
        return songRepository.save(song);
    }
}
