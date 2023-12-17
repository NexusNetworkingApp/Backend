package com.nexus.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    // You can add custom query methods here if needed
    List<Like> findByReceiverAccountId(Long receiverId);
}
