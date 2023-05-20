package com.backend.musicservice.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PlaylistTests {

    private Playlist playlist;

    @BeforeEach
    public void setUp() {
        this.playlist = new Playlist();
    }

    @Test
    public void testSetIdGetId() {
        playlist.setId(1L);
        Assertions.assertEquals(1L, playlist.getId());
    }

    @Test
    public void testSetNameGetName() {
        playlist.setName("test");
        Assertions.assertEquals("test", playlist.getName());
    }

    @Test
    public void testSetLikesGetLikes() {
        playlist.setLikes(1);
        Assertions.assertEquals(1, playlist.getLikes());
    }

    @Test
    public void testSetNrOfSongsGetNrOfSongs() {
        playlist.setNr_of_songs(1);
        Assertions.assertEquals(1, playlist.getNr_of_songs());
    }

    @Test
    public void testSetDurationGetDuration() {
        playlist.setDuration(1);
        Assertions.assertEquals(1, playlist.getDuration());
    }

    @Test
    public void testSetCreatedAtGetCreatedAt() {
        playlist.setCreated_at(LocalDate.parse("2022-04-23"));
        Assertions.assertEquals("2022-04-23", playlist.getCreated_at().toString());
    }

    @Test
    public void testSetCreatorIdGetCreatorId() {
        playlist.setCreator_id("1234");
        Assertions.assertEquals("1234", playlist.getCreator_id());
    }



}
