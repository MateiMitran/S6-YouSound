package com.backend.userservice.Controller;


import com.backend.userservice.Entities.Community;
import com.backend.userservice.Services.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communities")
public class CommunityController {

    @Autowired
    CommunityService communityService;


    @GetMapping("")
    public ResponseEntity<List<Community>> getAllCommunities() {
        return ResponseEntity.ok(communityService.getAllCommunities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Community> getCommunityById(@PathVariable String id) {
        return ResponseEntity.ok(communityService.findCommunityById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Community> createCommunity(@RequestBody Community c) {
        return ResponseEntity.ok(communityService.createCommunity(c));
    }

    @GetMapping("/{c_id}/artist/")
    public ResponseEntity<String> getArtistIdFromCommunity(@PathVariable String c_id) {
        return ResponseEntity.ok(communityService.getArtistIdOfCommunity(c_id));
    }
}
