package main.java.com.nexus.api.business;

import com.nexus.api.data.Message;
import com.nexus.api.data.MessageRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MessageService {

    private static final Logger logger = Logger.getLogger(MessageService.class.getName());
    
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages(Long accountId, Long otherAccountId) {
        logger.info("Fetching messages between accounts " + accountId + " and " + otherAccountId);
        return messageRepository.findBySender_AccountIdAndReceiver_AccountIdOrSender_AccountIdAndReceiver_AccountIdOrderByMessageDate(
                accountId, otherAccountId, otherAccountId, accountId);
    }

    @Transactional
    public void saveMessage(Message message) {
        message.setStatus("SENT"); // Default status
        logger.info("Saving message: " + message);
        messageRepository.save(message);
        logger.info("Message saved with ID: " + message.getMessageId());
    }

    @Transactional
    public void updateMessageStatus(Long messageId, String status) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new RuntimeException("Message not found"));
        message.setStatus(status);
        messageRepository.save(message);
    }
}
