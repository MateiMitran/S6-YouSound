package com.backend.musicservice.services;

import com.backend.musicservice.entities.Playlist;
import com.backend.musicservice.repositories.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        expectedPlaylists.add(new Playlist(1L, "testName", "testDesc", LocalDate.parse("2023-04-23"), "testPic", "testFile", 120, "1234"));
        expectedPlaylists.add(new Playlist(2L, "testName", "testDesc", LocalDate.parse("2023-04-23"), "testPic", "testFile", 120, "1234"));
        expectedPlaylists.add(new Playlist(3L, "testName", "testDesc", LocalDate.parse("2023-04-23"), "testPic", "testFile", 120, "1234"));

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
        Playlist playlist = new Playlist(1L, "testName", "testDesc", LocalDate.parse("2023-04-23"), "testPic", "testFile", 120, "1234");
        when(playlistRepository.findById(1L)).thenReturn(java.util.Optional.of(playlist));

        Playlist actualPlaylist = playlistService.getPlaylistById(1L);

        assertEquals(playlist, actualPlaylist);

        verify(playlistRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetPlaylistsOfUser() {
        List<Playlist> expectedPlaylists = new ArrayList<>();
        expectedPlaylists.add(new Playlist(1L, "testName", "testDesc", LocalDate.parse("2023-04-23"), "testPic", "testFile", 120, "1234"));
        expectedPlaylists.add(new Playlist(2L, "testName", "testDesc", LocalDate.parse("2023-04-23"), "testPic", "testFile", 120, "1234"));
        expectedPlaylists.add(new Playlist(3L, "testName", "testDesc", LocalDate.parse("2023-04-23"), "testPic", "testFile", 120, "1234"));
        when(playlistRepository.findAll()).thenReturn(expectedPlaylists);
        List<Playlist> actualPlaylists = playlistService.getPlaylistsOfUser("1234");
        assertEquals(expectedPlaylists.size(), actualPlaylists.size());
        for (int i = 0; i < expectedPlaylists.size(); i++) {
            assertEquals(expectedPlaylists.get(i), actualPlaylists.get(i));
        }
        verify(playlistRepository, times(1)).findAll();
    }

    @Test
    public void testCreatePlaylist() {
        Playlist playlist = new Playlist(1L, "testName", "testDesc", LocalDate.parse("2023-04-23"), "testPic", "testFile", 120, "1234");
        when(playlistRepository.save(playlist)).thenReturn(playlist);
        Playlist actualPlaylist = playlistService.createPlaylist(playlist);
        assertEquals(playlist, actualPlaylist);
        verify(playlistRepository, times(1)).save(playlist);
    }
}
