package com.backend.musicservice.entities;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;


public class SongTests {

    private Song song;

    @BeforeEach
    public void setUp() {
        this.song = new Song();
    }

    @Test
    public void testSetIdGetId() {
        song.setId(1L);
        Assertions.assertEquals(1L, song.getId());
    }

    @Test
    public void testSetNameGetName() {
        song.setName("test");
        Assertions.assertEquals("test", song.getName());
    }

    @Test
    public void testSetPictureGetPicture() {
        song.setPicture("test");
        Assertions.assertEquals("test", song.getPicture());
    }

    @Test
    public void testSetGenreGetGenre() {
        song.setGenre("test");
        Assertions.assertEquals("test", song.getGenre());
    }

    @Test
    public void testSetAlbumIdGetAlbumId() {
        song.setAlbum_id(1L);
        Assertions.assertEquals(1L, song.getAlbum_id());
    }

    @Test
    public void testSetArtistIdGetArtistId() {
        song.setArtist_id("test");
        Assertions.assertEquals("test", song.getArtist_id());
    }

    @Test
    public void testSetPlaysGetPlays() {
        song.setPlays(10);
        Assertions.assertEquals(10, song.getPlays());
    }

    @Test
    public void testSetDurationGetDuration() {
        song.setDuration(120);
        Assertions.assertEquals(120, song.getDuration());
    }

    @Test
    public void testEquals() {
        Long id = 1L;
        Song song1 = new Song();
        Song song2 = new Song();
        song1.setId(id);
        song2.setId(id);
        Assertions.assertEquals(song1, song2);
    }

    @Test
    public void testSongConstructor() {
        Song song = new Song(1L, "testName", "testDesc", LocalDateTime.parse("2023-04-23T10:15:32"), "testPic", "testFile", 120, "testGenre", "1234");
        Assertions.assertEquals(1L, song.getId());
        Assertions.assertEquals("testName", song.getName());
        Assertions.assertEquals("testGenre", song.getGenre());
    }



}
