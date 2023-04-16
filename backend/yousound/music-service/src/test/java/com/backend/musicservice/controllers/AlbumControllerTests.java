package com.backend.musicservice.controllers;

import com.backend.musicservice.entities.Album;
import com.backend.musicservice.services.AlbumService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AlbumController.class)
@ContextConfiguration(classes = AlbumController.class)
public class AlbumControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService albumService;

    @Test
    public void testFindAlbumById() throws Exception {
        Album album = new Album(1L,"test", LocalDateTime.parse("2023-04-23T10:15:32"), 1, 1, "1234");
        Mockito.when(albumService.getAlbumById(1L)).thenReturn(album);

        mockMvc.perform(get("/api/albums/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.artist_id").value("1234"))
                .andExpect(jsonPath("$.name").value("test"));
    }

    @Test
    public void testFindAlbums() throws Exception {
        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L,"test", LocalDateTime.parse("2023-04-23T10:15:32"), 1, 1, "1234"));
        albums.add(new Album(2L,"test", LocalDateTime.parse("2023-04-23T10:15:32"), 1, 1, "1234"));
        Mockito.when(albumService.getAllAlbums()).thenReturn(albums);

        mockMvc.perform(get("/api/albums"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].artist_id").value("1234"))
                .andExpect(jsonPath("$[0].name").value("test"))
                .andExpect(jsonPath("$[1].artist_id").value("1234"))
                .andExpect(jsonPath("$[1].name").value("test"));
    }

    @Test
    public void testCreateAlbum() throws Exception {
        Album album = new Album(1L,"test", LocalDateTime.parse("2023-04-23T10:15:32"), 1, 1, "1234");
        Mockito.when(albumService.createAlbum(album)).thenReturn(album);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String json = mapper.writeValueAsString(album);

        mockMvc.perform(post("/api/albums/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.artist_id").value("1234"))
                .andExpect(jsonPath("$.name").value("test"));
    }

    @Test
    public void testGetAllAlbumsByArtist() throws Exception {
        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L,"test", LocalDateTime.parse("2023-04-23T10:15:32"), 1, 1, "1234"));
        albums.add(new Album(2L,"test", LocalDateTime.parse("2023-04-23T10:15:32"), 1, 1, "1234"));
        Mockito.when(albumService.getAlbumsByArtistId("1234")).thenReturn(albums);
        mockMvc.perform(get("/api/albums/by/1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].artist_id").value("1234"))
                .andExpect(jsonPath("$[0].name").value("test"))
                .andExpect(jsonPath("$[1].artist_id").value("1234"))
                .andExpect(jsonPath("$[1].name").value("test"));

    }

}
