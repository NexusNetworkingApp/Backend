package com.nexus.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    // You can add custom query methods here if needed
    List<Like> findByReceiverAccountId(Long receiverId);

    @Query("SELECT l FROM Like l WHERE l.sender.accountId = :accountId OR l.receiver.accountId = :accountId")
    List<Like> findRelatedByAccountId(@Param("accountId") Long accountId);
}
