package com.backend.userservice.controllers;


import com.backend.userservice.entities.Community;
import com.backend.userservice.services.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communities")
public class CommunityController {


    @Autowired
    private CommunityService communityService;

    @GetMapping("")
    public ResponseEntity<List<Community>> getAllCommunities() {
        return ResponseEntity.ok(communityService.getAllCommunities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Community> getCommunityById(@PathVariable String id) {
        return ResponseEntity.ok(communityService.getCommunityFromId(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Community> createCommunity(@RequestBody Community c) {
        return ResponseEntity.ok(communityService.createCommunity(c));
    }

    @GetMapping("/{cId}/artist/")
    public ResponseEntity<String> getArtistIdFromCommunity(@PathVariable String cId) {
        return ResponseEntity.ok(communityService.getArtistIdOfCommunity(cId));
    }
}
