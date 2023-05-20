//package com.backend.userservicetests.services;
//
//
//import com.backend.userservice.entities.Community;
//import com.backend.userservice.repositories.CommunityRepository;
//import com.backend.userservice.services.CommunityService;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ContextConfiguration(classes = CommunityService.class)
//public class CommunityServiceTests {
//
//    @Autowired
//    private CommunityService communityService;
//
//    @MockBean
//    private CommunityRepository communityRepository;
//
//    @Test
//    public void testGetArtistIdOfCommunity() {
//        String id = "1";
//        String artistId = "2";
//        Community c = new Community();
//        c.setArtist_id(artistId);
//        Mockito.when(communityRepository.findById(id)).thenReturn(java.util.Optional.of(c));
//        String result = communityService.getArtistIdOfCommunity(id);
//        assertEquals(artistId, result);
//    }
//
//    @Test
//    public void testCheckIfUserIsInCommunity() {
//        String id = "1";
//        String userId = "2";
//        Community c = new Community();
//        c.addUserId(userId);
//        Mockito.when(communityRepository.findById(id)).thenReturn(java.util.Optional.of(c));
//        boolean result = communityService.checkIfUserIsInCommunity(id, userId);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testGetCommunityFromId() {
//        String id = "1";
//        Community c = new Community();
//        Mockito.when(communityRepository.findById(id)).thenReturn(java.util.Optional.of(c));
//        Community result = communityService.getCommunityFromId(id);
//        assertEquals(c, result);
//    }
//
//    @Test
//    public void testCreateCommunity() {
//        String id = "1";
//        Community c = new Community();
//        Mockito.when(communityRepository.save(c)).thenReturn(c);
//        Community result = communityService.createCommunity(c);
//        assertEquals(c, result);
//    }
//
//    @Test
//    public void testDeleteCommunity() {
//        String id = "1";
//        Community c = new Community();
//        Mockito.when(communityRepository.findById(id)).thenReturn(java.util.Optional.of(c));
//        communityService.deleteCommunity(c);
//        Mockito.verify(communityRepository, Mockito.times(1)).delete(c);
//    }
//
//    @Test
//    public void testGetAllCommunities() {
//        Community c = new Community();
//        Mockito.when(communityRepository.findAll()).thenReturn(java.util.List.of(c));
//        java.util.List<Community> result = communityService.getAllCommunities();
//        assertEquals(1, result.size());
//        assertEquals(c, result.get(0));
//    }
//}
