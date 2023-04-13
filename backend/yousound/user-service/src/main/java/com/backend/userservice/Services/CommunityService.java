package com.backend.userservice.Services;


import com.backend.userservice.Entities.Community;
import com.backend.userservice.Repositories.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityService {

    @Autowired
    CommunityRepository communityRepository;

    public Community findCommunityById(String id) {
        return getCommunityFromId(id);
    }

    public String getArtistIdOfCommunity(String id) {
        return getCommunityFromId(id).getArtist_id();
    }



    public boolean checkIfUserIsInCommunity(String communityId, String userId) {
        return getCommunityFromId(communityId).getUser_ids().contains(userId);
    }


    private Community getCommunityFromId(String id) {
        Optional<Community> community = communityRepository.findById(id);

        if (community.isPresent()) {
           return community.get();
        }

        return null;
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


}



