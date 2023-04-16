package com.backend.musicservice.controllers;
import com.backend.musicservice.entities.Playlist;
import com.backend.musicservice.services.PlaylistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
@WebMvcTest(PlaylistController.class)
@ContextConfiguration(classes = PlaylistController.class)
public class PlaylistControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaylistService playlistService;

    @Test
    public void testGetPlaylistById() throws Exception {
        Playlist playlist = new Playlist(1L, "testName", "1234");
        Mockito.when(playlistService.getPlaylistById(1L)).thenReturn(playlist);
        mockMvc.perform(get("/api/playlists/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("testName"))
                .andExpect(jsonPath("$.creator_id").value("1234"));
    }

    @Test
    public void testCreatePlaylist() throws Exception {
        Playlist playlist = new Playlist(1L, "testName", "1234");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(playlist);
        Mockito.when(playlistService.createPlaylist(playlist)).thenReturn(playlist);
        mockMvc.perform(post("/api/playlists/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("testName"))
                .andExpect(jsonPath("$.creator_id").value("1234"));
    }

    @Test
    public void testGetAllPlaylists() throws Exception {
        Playlist playlist1 = new Playlist(1L, "testName1", "1234");
        Playlist playlist2 = new Playlist(2L, "testName2", "1234");
        List<Playlist> playlistList = new ArrayList<>();
        playlistList.add(playlist1);
        playlistList.add(playlist2);
        Mockito.when(playlistService.getAllPlaylists()).thenReturn(playlistList);
        mockMvc.perform(get("/api/playlists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("testName1"))
                .andExpect(jsonPath("$[0].creator_id").value("1234"))
                .andExpect(jsonPath("$[1].name").value("testName2"))
                .andExpect(jsonPath("$[1].creator_id").value("1234"));
    }

    @Test
    public void testGetAllPlaylistsFromUser() throws Exception {
        Playlist playlist1 = new Playlist(1L, "testName1", "1234");
        Playlist playlist2 = new Playlist(2L, "testName2", "1234");
        List<Playlist> playlistList = new ArrayList<>();
        playlistList.add(playlist1);
        playlistList.add(playlist2);
        Mockito.when(playlistService.getPlaylistsOfUser("1234")).thenReturn(playlistList);
        mockMvc.perform(get("/api/playlists/user/1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("testName1"))
                .andExpect(jsonPath("$[0].creator_id").value("1234"))
                .andExpect(jsonPath("$[1].name").value("testName2"))
                .andExpect(jsonPath("$[1].creator_id").value("1234"));
    }
}
