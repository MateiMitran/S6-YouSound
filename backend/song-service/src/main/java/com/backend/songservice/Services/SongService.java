package com.backend.songservice.Services;

import com.backend.songservice.Entities.Song;
import com.backend.songservice.Repositories.SongRepository;
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
