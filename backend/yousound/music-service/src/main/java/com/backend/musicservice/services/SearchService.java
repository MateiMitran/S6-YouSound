package com.backend.musicservice.services;

import com.backend.musicservice.entities.ContentEntity;
import com.backend.musicservice.entities.SearchResult;
import com.backend.musicservice.repositories.AlbumRepository;
import com.backend.musicservice.repositories.PlaylistRepository;
import com.backend.musicservice.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    public List<SearchResult> search(String query) {
        List<SearchResult> results = new ArrayList<>();
        results.addAll(songRepository.search(query));
        results.addAll(albumRepository.search(query));
        results.addAll(playlistRepository.search(query));
        return results;
    }

    public ContentEntity all() {
        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setSongs(songRepository.findAll());
        contentEntity.setAlbums(albumRepository.findAll());
        contentEntity.setPlaylists(playlistRepository.findAll());
        return contentEntity;
    }

    public Boolean deleteById(Long id) {
        if (songRepository.findById(id).isPresent()) {
            songRepository.deleteById(id);
            return true;
        }
        if (playlistRepository.findById(id).isPresent()) {
            playlistRepository.deleteById(id);
            return true;
        }
        if (albumRepository.findById(id).isPresent()) {
            albumRepository.deleteById(id);
            return true;
        }
        return false;
    }


}