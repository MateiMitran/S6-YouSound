package com.backend.socialservice.services;

import com.backend.socialservice.entities.Reel;
import com.backend.socialservice.repositories.ReelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReelService {

    @Autowired
    private final ReelRepository reelRepository;

    public ReelService(ReelRepository reelRepository) {
        this.reelRepository = reelRepository;
    }

    public Reel createReel(Reel reel) {
        return reelRepository.save(reel);
    }

    public void deleteByUserId(String userId) {
        List<Reel> reels = reelRepository.findAll();
        for (Reel reel: reels) {
            if (reel.getUser_id().equals(userId)) {
                reelRepository.delete(reel);
            }
        }
    }

    public boolean deleteReel(Reel reel) {
        reelRepository.delete(reel);
        return true;
    }

    public Reel getReelById(String id) {
        Optional<Reel> reel = reelRepository.findById(id);
        return reel.orElse(null);
    }

    public List<Reel> getAllReels() {
        return reelRepository.findAll();
    }

    public boolean likeReel(String id) {
        Optional<Reel> reel = reelRepository.findById(id);
        if (reel.isPresent()) {
            reel.get().setLikes(reel.get().getLikes()+1);
            reelRepository.save(reel.get());
            return true;
        }
        return false;
    }

    public boolean unlikeReel(String id) {
        Optional<Reel> reel = reelRepository.findById(id);
        if (reel.isPresent()) {
            reel.get().setLikes(reel.get().getLikes()-1);
            reelRepository.save(reel.get());
            return true;
        }
        return false;
    }


}
