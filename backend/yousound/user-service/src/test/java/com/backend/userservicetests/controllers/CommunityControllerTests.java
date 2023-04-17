package com.backend.userservicetests.controllers;

import com.backend.userservice.controllers.CommunityController;
import com.backend.userservice.entities.Community;
import com.backend.userservice.services.CommunityService;
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
@WebMvcTest(CommunityController.class)
@ContextConfiguration(classes = CommunityController.class)
public class CommunityControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommunityService communityService;

    @Test
    public void testGetAllCommunities() throws Exception {
        List<Community> communities = new ArrayList<>();
        communities.add(new Community("1", "1234", "testDesc"));
        communities.add(new Community("2", "1234","testDesc"));
        Mockito.when(communityService.getAllCommunities()).thenReturn(communities);

        mockMvc.perform(get("/api/communities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].artist_id").value("1234"))
                .andExpect(jsonPath("$[0].description").value("testDesc"))
                .andExpect(jsonPath("$[1].artist_id").value("1234"))
                .andExpect(jsonPath("$[1].description").value("testDesc"));

    }

    @Test
    public void testGetCommunityById() throws Exception {
        Community community = new Community("1", "1234", "testDesc");
        Mockito.when(communityService.getCommunityFromId("1")).thenReturn(community);

        mockMvc.perform(get("/api/communities/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.artist_id").value("1234"))
                .andExpect(jsonPath("$.description").value("testDesc"));
    }

    @Test
    public void testCreateCommunity() throws Exception {
        Community community = new Community("1", "1234", "testDesc");
        ObjectMapper objectMapper = new ObjectMapper();
        String communityJson = objectMapper.writeValueAsString(community);
        Mockito.when(communityService.createCommunity(community)).thenReturn(community);

        mockMvc.perform(post("/api/communities/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(communityJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.artist_id").value("1234"))
                .andExpect(jsonPath("$.description").value("testDesc"));
    }
}
