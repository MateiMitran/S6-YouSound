package com.backend.musicservice.Services;


import com.backend.musicservice.Entities.Album;
import com.backend.musicservice.Repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository aRepo;

    public List<Album> getAllAlbums() {
        return aRepo.findAll();
    }

    public Album getAlbumById(Long id) {
        return aRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
    }

    public List<Album> getAlbumsByArtistId(String artist_id) {
        List<Album> albums = new ArrayList<>();

        getAllAlbums().forEach(album -> {
            if (album.getArtist_id() == artist_id) {
                albums.add(album);
            }
        });

        return albums;
    }

    public Album createAlbum(Album album) {
        return aRepo.save(album);
    }

}
