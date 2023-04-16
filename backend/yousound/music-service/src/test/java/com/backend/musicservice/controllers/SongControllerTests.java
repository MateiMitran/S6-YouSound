package com.backend.musicservice.controllers;

import com.backend.musicservice.entities.Song;
import com.backend.musicservice.services.SongService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(SongController.class)
@ContextConfiguration(classes = SongController.class)
public class SongControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongService songService;

    @Test
    public void testGetSongById() throws Exception {
        Song song = new Song(1L, "testName", "testGenre");
        Mockito.when(songService.getSongById(1L)).thenReturn(song);
        mockMvc.perform(get("/api/songs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("testName"))
                .andExpect(jsonPath("$.genre").value("testGenre"));
    }

    @Test
    public void testCreateSong() throws Exception {
        Song song = new Song(1L, "testName", "testGenre");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(song);
        Mockito.when(songService.createSong(song)).thenReturn(song);
        mockMvc.perform(post("/api/songs/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("testName"))
                .andExpect(jsonPath("$.genre").value("testGenre"));
    }

    @Test
    public void testGetAllSongs() throws Exception {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song(1L, "testName", "testGenre"));
        songs.add(new Song(2L, "testName", "testGenre"));
        Mockito.when(songService.getAllSongs()).thenReturn(songs);
        mockMvc.perform(get("/api/songs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("testName"))
                .andExpect(jsonPath("$[0].genre").value("testGenre"))
                .andExpect(jsonPath("$[1].name").value("testName"))
                .andExpect(jsonPath("$[1].genre").value("testGenre"));
    }
}
