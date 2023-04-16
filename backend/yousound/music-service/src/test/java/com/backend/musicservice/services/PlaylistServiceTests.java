package com.backend.musicservice.services;

import com.backend.musicservice.entities.Playlist;
import com.backend.musicservice.repositories.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class PlaylistServiceTests {

    @Mock
    private PlaylistRepository playlistRepository;

    @InjectMocks
    private PlaylistService playlistService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPlaylists() {
        List<Playlist> expectedPlaylists = new ArrayList<>();
        expectedPlaylists.add(new Playlist(1L, "testName", "testGenre"));
        expectedPlaylists.add(new Playlist(2L, "testName1", "testGenre1"));
        expectedPlaylists.add(new Playlist(3L, "testName2", "testGenre2"));

        when(playlistRepository.findAll()).thenReturn(expectedPlaylists);

        List<Playlist> actualPlaylists = playlistService.getAllPlaylists();

        assertEquals(expectedPlaylists.size(), actualPlaylists.size());

        for (int i = 0; i < expectedPlaylists.size(); i++) {
            assertEquals(expectedPlaylists.get(i), actualPlaylists.get(i));
        }

        verify(playlistRepository, times(1)).findAll();
    }

    @Test
    public void testGetPlaylistById() {
        Playlist playlist = new Playlist(1L, "testName", "testGenre");
        when(playlistRepository.findById(1L)).thenReturn(java.util.Optional.of(playlist));

        Playlist actualPlaylist = playlistService.getPlaylistById(1L);

        assertEquals(playlist, actualPlaylist);

        verify(playlistRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetPlaylistsOfUser() {
        List<Playlist> expectedPlaylists = new ArrayList<>();
        expectedPlaylists.add(new Playlist(1L, "testName", "testGenre"));
        expectedPlaylists.add(new Playlist(2L, "testName1", "testGenre"));
        expectedPlaylists.add(new Playlist(3L, "testName2", "testGenre"));
        when(playlistRepository.findAll()).thenReturn(expectedPlaylists);
        List<Playlist> actualPlaylists = playlistService.getPlaylistsOfUser("testGenre");
        assertEquals(expectedPlaylists.size(), actualPlaylists.size());
        for (int i = 0; i < expectedPlaylists.size(); i++) {
            assertEquals(expectedPlaylists.get(i), actualPlaylists.get(i));
        }
        verify(playlistRepository, times(1)).findAll();
    }

    @Test
    public void testCreatePlaylist() {
        Playlist playlist = new Playlist(1L, "testName", "testGenre");
        when(playlistRepository.save(playlist)).thenReturn(playlist);
        Playlist actualPlaylist = playlistService.createPlaylist(playlist);
        assertEquals(playlist, actualPlaylist);
        verify(playlistRepository, times(1)).save(playlist);
    }
}
