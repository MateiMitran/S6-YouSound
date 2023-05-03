package com.backend.musicservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ContentEntity {

    private List<Song> songs;
    private List<Album> albums;
    private List<Playlist> playlists;

}
