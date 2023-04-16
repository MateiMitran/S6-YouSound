package com.backend.musicservice.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AlbumTests {

    private Album album;

    @BeforeEach
    public void setUp() {
        album = new Album();
    }

    @Test
    public void testSetIdGetId() {
        album.setId(1L);
        assertEquals(1L, album.getId());
    }

    @Test
    public void testSetNameGetName() {
        album.setName("test");
        assertEquals("test", album.getName());
    }

    @Test
    public void testSetCreatedAtGetCreatedAt() {
        album.setCreated_at(LocalDateTime.parse("2023-04-23T10:15:32"));
        Assertions.assertEquals("2023-04-23T10:15:32", album.getCreated_at().toString());
    }

    @Test
    public void testNrOfSongsGetNrOfSongs() {
        album.setNr_of_songs(1);
        assertEquals(1, album.getNr_of_songs());
    }

    @Test
    public void testSetDurationGetDuration() {
        album.setDuration(1);
        assertEquals(1, album.getDuration());
    }

    @Test
    public void testSetArtistIdGetArtistId() {
        album.setArtist_id("1234");
        assertEquals("1234", album.getArtist_id());
    }

    @Test
    public void testAlbumConstructor() {
        Album album = new Album(1L,"testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 1, 1, "1234");
        assertEquals("testName", album.getName());
        assertEquals("2023-04-23T10:15:32", album.getCreated_at().toString());
        assertEquals(1, album.getNr_of_songs());
        assertEquals(1, album.getDuration());
        assertEquals("1234", album.getArtist_id());
    }

}
