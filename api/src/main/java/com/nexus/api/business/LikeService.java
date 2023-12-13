package com.nexus.api.business;

import com.nexus.api.data.Like;
import com.nexus.api.data.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void createLike (Like like) {
        likeRepository.save(like);
    }
    // You can use likeRepository to perform database operations related to likes.

    // Add methods to interact with likes as needed, e.g., save, find, delete, etc.
}
