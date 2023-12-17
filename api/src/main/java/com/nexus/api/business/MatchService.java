package com.nexus.api.business;

import com.nexus.api.data.Like;
import com.nexus.api.data.LikeRepository;
import com.nexus.api.data.Match;
import com.nexus.api.data.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final LikeRepository likeRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository, LikeRepository likeRepository) {
        this.matchRepository = matchRepository;
        this.likeRepository = likeRepository;
    }


    public void createMatch(Like like) {
        // Save new match
        Match newMatch = new Match();
        newMatch.setUser1(like.getReceiver());
        newMatch.setUser2(like.getSender());
        newMatch.setMatchDate(new Date());
        matchRepository.save(newMatch);

        // Remove like
        likeRepository.delete(like);
    }


    public List<Match> getMatchesByAccountId(Long accountId) {
        // Implement logic to fetch matches for both user1 and user2 based on the accountId
        return matchRepository.findByUser1AccountIdOrUser2AccountId(accountId, accountId);
    }

}
