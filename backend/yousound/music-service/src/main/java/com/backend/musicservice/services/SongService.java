package com.backend.musicservice.services;

import com.backend.musicservice.entities.Like;
import com.backend.musicservice.entities.Song;
import com.backend.musicservice.repositories.LikeRepository;
import com.backend.musicservice.repositories.SongRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class SongService {


    Logger log = Logger.getLogger(SongService.class.getName());

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private LikeRepository likeRepository;

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(Long id) {
        return songRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    public Boolean deleteSongById(Long id) {
        Song song = songRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        songRepository.delete(song);
        return true;
    }


    public void deleteLikedSongsOfUser(String user_id) {
        List<Like> likes = likeRepository.findAll();
        for (Like like: likes) {
            if (like.getUser_id().equals(user_id)) {
                likeRepository.delete(like);
            }
        }
    }

    public List<Song> getLikedSongsOfUser(String user_id) {
        List<Like> likes = likeRepository.findAll();
        List<Song> songs = new ArrayList<>();
        for (Like like: likes) {
            if (like.getUser_id().equals(user_id)) {
                songs.add(songRepository.findById(like.getSong_id()).orElseThrow(ResourceNotFoundException::new));
            }
        }
        return songs;
    }

    public Like addToLikedSongs(String userId,Long songId) {
        Like like = new Like();
        like.setUser_id(userId);
        like.setSong_id(songId);
        return likeRepository.save(like);
    }

    public SongAnalytic calculateAnalytics(Long id) {

        Song song = songRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        log.info("Song plays: " + song.getPlays());
        log.info("Song duration: " + song.getDuration());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Song> entity = new HttpEntity<>(song, headers);
        ResponseEntity<SongAnalytic> response = restTemplate.exchange(
                "https://europe-west1-brave-drummer-386508.cloudfunctions.net/musicAnalytics",
                HttpMethod.POST,
                entity,
                SongAnalytic.class
        );

        return response.getBody();

    }


}


class SongAnalytic {

    @JsonProperty("totalPlayTime")
    private int totalPlayTime;

    public SongAnalytic(int totalPlayTime) {
        this.totalPlayTime = totalPlayTime;
    }

    public SongAnalytic() {
    }

    public int getTotalPlayTime() {
        return totalPlayTime;
    }

    public void setTotalPlayTime(int totalPlayTime) {
        this.totalPlayTime = totalPlayTime;
    }

}
