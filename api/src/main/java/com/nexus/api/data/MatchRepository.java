package com.nexus.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    // You can add custom query methods here if needed
    List<Match> findByUser1AccountIdOrUser2AccountId(Long user1AccountId, Long user2AccountId);
}
