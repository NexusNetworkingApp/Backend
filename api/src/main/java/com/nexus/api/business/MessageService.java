package com.nexus.api.business;

import com.nexus.api.data.Message;
import com.nexus.api.data.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages(Long accountId, Long otherAccountId) {
        // Implement the logic to retrieve messages from the repository
        return messageRepository.findBySender_AccountIdAndReceiver_AccountIdOrSender_AccountIdAndReceiver_AccountIdOrderByMessageDate(
                accountId, otherAccountId, otherAccountId, accountId);
    }

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}
