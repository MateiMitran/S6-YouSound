package com.backend.musicservice.controllers;


import com.backend.musicservice.entities.ContentEntity;
import com.backend.musicservice.entities.SearchResult;
import com.backend.musicservice.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<List<SearchResult>> search(@RequestParam String query) {
        return ResponseEntity.ok(searchService.search(query));
    }

    @GetMapping("/all")
    public ResponseEntity<ContentEntity> all() {
        return ResponseEntity.ok(searchService.all());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(searchService.deleteById(id));
    }
}
