package com.nexus.api.business;

import com.nexus.api.data.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    public MatchService(MatchRepository matchRepository) {
    }

    // You can use matchRepository to perform database operations related to matches.

    // Add methods to interact with matches as needed, e.g., save, find, delete, etc.
}
