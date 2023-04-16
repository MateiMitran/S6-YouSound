package com.backend.musicservice.services;

import com.backend.musicservice.repositories.SongRepository;
import com.backend.musicservice.entities.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SongServiceTests {

    @Mock
    private SongRepository songRepository;


    @InjectMocks
    private SongService songService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSongs() {

        List<Song> expectedSongs = new ArrayList<>();

        expectedSongs.add(new Song(1L, "testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 120, "testGenre", "1234"));
        expectedSongs.add(new Song(2L, "testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 120, "testGenre", "1234"));
        expectedSongs.add(new Song(3L, "testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 120, "testGenre", "1234"));

        when(songRepository.findAll()).thenReturn(expectedSongs);

        List<Song> actualSongs = songService.getAllSongs();

        assertEquals(expectedSongs.size(), actualSongs.size());

        for (int i = 0; i < expectedSongs.size(); i++) {
            assertEquals(expectedSongs.get(i), actualSongs.get(i));
        }

        verify(songRepository, times(1)).findAll();
    }


    @Test
    public void testGetSongById() {
        Song expectedSong = new Song(1L, "testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 120, "testGenre", "1234");

        when(songRepository.findById(1L)).thenReturn(java.util.Optional.of(expectedSong));

        Song actualSong = songService.getSongById(1L);

        assertEquals(expectedSong, actualSong);

        verify(songRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateSong() {
        Song expectedSong = new Song(1L, "testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 120, "testGenre", "1234");

        when(songRepository.save(expectedSong)).thenReturn(expectedSong);

        Song actualSong = songService.createSong(expectedSong);

        assertEquals(expectedSong, actualSong);

        verify(songRepository, times(1)).save(expectedSong);
    }

}
