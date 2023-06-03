package com.backend.socialservice.controllers;


import com.backend.socialservice.entities.Reel;
import com.backend.socialservice.services.ReelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reels")
public class ReelController {

    @Autowired
    private ReelService reelService;


    @PostMapping("/create")
    public ResponseEntity<Reel> createReel(@Validated @RequestBody Reel reel) {
        return ResponseEntity.ok(reelService.createReel(reel));
    }

    //fix this
    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteReel(@Validated @RequestBody Reel reel) {
        return ResponseEntity.ok(reelService.deleteReel(reel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reel> getReelById(@PathVariable String id) {
        return ResponseEntity.ok(reelService.getReelById(id));
    }

    @GetMapping
    public ResponseEntity<List<Reel>> getAllReels() {
        return ResponseEntity.ok(reelService.getAllReels());
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<Boolean> likeReel(@PathVariable String id) {
        return ResponseEntity.ok(reelService.likeReel(id));
    }

    @PostMapping("/unlike/{id}")
    public ResponseEntity<Boolean> unlikeReel(@PathVariable String id) {
        return ResponseEntity.ok(reelService.unlikeReel(id));
    }





}
