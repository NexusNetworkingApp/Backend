package main.java.com.nexus.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySender_AccountIdAndReceiver_AccountIdOrSender_AccountIdAndReceiver_AccountIdOrderByMessageDate(
            Long senderId, Long receiverId, Long receiverId2, Long senderId2);
}
