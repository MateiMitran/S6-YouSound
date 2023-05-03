package com.backend.musicservice.services;


import com.backend.musicservice.entities.Playlist;
import com.backend.musicservice.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PlaylistService {

    @Autowired
    PlaylistRepository pRepo;

    public List<Playlist> getAllPlaylists() {
        return pRepo.findAll();
    }

    public Playlist getPlaylistById(Long id) {
        return pRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Playlist> getPlaylistsOfUser(String user_id) {
        List<Playlist> userPlaylists = new ArrayList<>();

        getAllPlaylists().forEach(playlist -> {
            if (Objects.equals(playlist.getCreator_id(), user_id)) {
                userPlaylists.add(playlist);
            }
        });

        return userPlaylists;
    }

    public Playlist createPlaylist(Playlist playlist) {

        playlist.setId(UUID.randomUUID().getLeastSignificantBits());
        return pRepo.save(playlist);
    }

    public Boolean deletePlaylistById(Long id) {
        Playlist playlist = pRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        pRepo.delete(playlist);
        return true;
    }
}
