package com.backend.musicservice.services;


import com.backend.musicservice.entities.Album;
import com.backend.musicservice.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository aRepo;

    public List<Album> getAllAlbums() {
        return aRepo.findAll();
    }

    public Album getAlbumById(Long id) {
        return aRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Album> getAlbumsByArtistId(String artist_id) {
        List<Album> albums = new ArrayList<>();

        getAllAlbums().forEach(album -> {
            if (Objects.equals(album.getArtist_id(), artist_id)) {
                albums.add(album);
            }
        });

        return albums;
    }

    public Album createAlbum(Album album) {
        return aRepo.save(album);
    }

}
