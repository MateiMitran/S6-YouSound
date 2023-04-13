package com.backend.musicservice.services;

import com.backend.musicservice.Repositories.SongRepository;
import com.backend.musicservice.Services.SongService;
import com.backend.musicservice.entities.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    public void testFindAllSongs() {

        List<Song> expectedSongs = new ArrayList<>();

        expectedSongs.add(new Song(1L, "testName", "testGenre"));
        expectedSongs.add(new Song(2L, "testName1", "testGenre1"));
        expectedSongs.add(new Song(3L, "testName2", "testGenre2"));

        when(songRepository.findAll()).thenReturn(expectedSongs);

        List<Song> actualSongs = songService.getAllSongs();

        assertEquals(expectedSongs.size(), actualSongs.size());

        for (int i = 0; i < expectedSongs.size(); i++) {
            assertEquals(expectedSongs.get(i), actualSongs.get(i));
        }

        verify(songRepository, times(1)).findAll();
    }


}
