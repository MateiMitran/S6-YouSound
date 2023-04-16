package com.backend.userservicetests.entities;

import com.backend.userservice.entities.Community;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommunityTests {

    private Community community;

    @BeforeEach
    public void setUp() {
        this.community = new Community();
    }


    @Test
    public void testSetIdGetId() {
        community.setId("test");
        assertEquals("test", community.getId());
    }

    @Test
    public void testSetArtistIdGetArtistId() {
        community.setArtist_id("1");
        assertEquals("1", community.getArtist_id());
    }

    @Test
    public void testSetDescriptionGetDescription() {
        community.setDescription("test");
        assertEquals("test", community.getDescription());
    }


    @Test
    public void testSetUserIdsGetUserIds() {
        community.addUserId("1");
        assertEquals(1, community.getUser_ids().size());
    }
}
