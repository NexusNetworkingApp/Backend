package main.java.com.nexus.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    // You can add custom query methods here if needed
    List<Match> findByUser1AccountIdOrUser2AccountId(Long user1AccountId, Long user2AccountId);
    @Query("SELECT m FROM Match m WHERE m.user1.accountId = :accountId OR m.user2.accountId = :accountId")
    List<Match> findRelatedMatchedAccount(@Param("accountId") Long accountId);
}
