package com.backend.musicservice.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
        album.setCreated_at(LocalDate.parse("2023-04-23"));
        Assertions.assertEquals("2023-04-23", album.getCreated_at().toString());
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


}
