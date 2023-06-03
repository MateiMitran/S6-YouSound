package com.backend.socialservice.controllers;


import com.backend.socialservice.services.CommunityService;
import com.backend.socialservice.entities.Community;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<Community> createCommunity(@Validated @RequestBody Community c) {
        return ResponseEntity.ok(communityService.createCommunity(c));
    }

    @GetMapping("/{cId}/artist/")
    public ResponseEntity<String> getArtistIdFromCommunity(@PathVariable String cId) {
        return ResponseEntity.ok(communityService.getArtistIdOfCommunity(cId));
    }
}
