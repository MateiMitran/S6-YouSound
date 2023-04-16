package com.backend.musicservice.services;

import com.backend.musicservice.entities.Album;
import com.backend.musicservice.repositories.AlbumRepository;
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
public class AlbumServiceTests {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumService albumService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAlbums() {
        List<Album> expectedAlbums = new ArrayList<>();
        Album album1 = new Album(1L,"testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 1, 1, "1234");
        Album album2 = new Album(2L,"testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 1, 1, "1234");
        Album album3 = new Album(3L,"testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 1, 1, "1234");

        when(albumRepository.findAll()).thenReturn(expectedAlbums);

        List<Album> actualAlbums = albumService.getAllAlbums();

        assertEquals(expectedAlbums.size(), actualAlbums.size());

        for (int i = 0; i < expectedAlbums.size(); i++) {
            assertEquals(expectedAlbums.get(i), actualAlbums.get(i));
        }

        verify(albumRepository, times(1)).findAll();
    }

    @Test
    public void testGetAlbumById() {
        Album album = new Album(1L,"testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 1, 1, "1234");
        when(albumRepository.findById(1L)).thenReturn(java.util.Optional.of(album));

        Album actualAlbum = albumService.getAlbumById(1L);

        assertEquals(album, actualAlbum);

        verify(albumRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAlbumsByArtistId() {
        List<Album> expectedAlbums = new ArrayList<>();
        Album album1 = new Album(1L,"testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 1, 1, "1234");
        Album album2 = new Album(2L,"testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 1, 1, "1234");
        Album album3 = new Album(3L,"testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 1, 1, "1234");

        when(albumRepository.findAll()).thenReturn(expectedAlbums);

        List<Album> actualAlbums = albumService.getAlbumsByArtistId("1234");

        assertEquals(expectedAlbums.size(), actualAlbums.size());

        for (int i = 0; i < expectedAlbums.size(); i++) {
            assertEquals(expectedAlbums.get(i), actualAlbums.get(i));
        }

        verify(albumRepository, times(1)).findAll();
    }

    @Test
    public void testCreateAlbum() {
        Album album = new Album(1L,"testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 1, 1, "1234");
        when(albumRepository.save(album)).thenReturn(album);

        Album actualAlbum = albumService.createAlbum(album);

        assertEquals(album, actualAlbum);

        verify(albumRepository, times(1)).save(album);
    }

}
