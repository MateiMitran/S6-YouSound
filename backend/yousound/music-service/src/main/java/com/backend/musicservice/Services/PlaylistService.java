package com.backend.musicservice.Services;


import com.backend.musicservice.entities.Playlist;
import com.backend.musicservice.Repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    PlaylistRepository pRepo;

    public List<Playlist> getAllPlaylists() {
        return pRepo.findAll();
    }

    public Playlist getPlaylistById(Long id) {
        return pRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
    }

    public List<Playlist> getPlaylistsOfUser(String user_id) {
        List<Playlist> userPlaylists = new ArrayList<>();

        getAllPlaylists().forEach(playlist -> {
            if (playlist.getCreator_id() == user_id) {
                userPlaylists.add(playlist);
            }
        });

        return userPlaylists;
    }

    public Playlist createPlaylist(Playlist playlist) {
        return pRepo.save(playlist);
    }
}
