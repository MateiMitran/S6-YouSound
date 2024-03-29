package com.backend.socialservice.services;


import com.backend.socialservice.entities.Community;
import com.backend.socialservice.repositories.CommunityRepository;
import com.backend.userservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    public String getArtistIdOfCommunity(String id) {
        Community c = getCommunityFromId(id);
        if (c==null) {
            return null;
        }
        return c.getArtist_id();
    }

//    public boolean checkIfUserIsInCommunity(String communityId, String userId) {
//        Community c = getCommunityFromId(communityId);
//        if (c==null) {
//            return false;
//        }
//        return c.getUser_ids().contains(userId);
//    }

    public Community getCommunityFromId(String id) {
        Optional<Community> community = communityRepository.findById(id);
        return community.orElse(null);
    }

    public Community createCommunity(Community c) {
        return communityRepository.save(c);
    }

    public void deleteCommunity(Community c) {
        communityRepository.delete(c);
    }

    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }


    public List<User> getAllUsersFromCommunityWithId(String id) throws ResourceNotFoundException {
        if (communityRepository.findById(id).isPresent()) {
            return communityRepository.findById(id).get().getUsers();
        }
        throw new ResourceNotFoundException();
    }

}



