package com.nexus.api.web;

import com.nexus.api.business.AccountService;
import com.nexus.api.business.MessageService;
import com.nexus.api.data.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/messages")
public class MessageController {

    private static final Logger logger = Logger.getLogger(MessageController.class.getName());
    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    public MessageController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @GetMapping("/{accountId}/{otherAccountId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long accountId, @PathVariable Long otherAccountId) {
        logger.info("Fetching messages between accounts " + accountId + " and " + otherAccountId);
        List<Message> messages = messageService.getMessages(accountId, otherAccountId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/send/{senderId}/{receiverId}/{content}")
    public ResponseEntity<Message> sendMessage(
            @PathVariable Long senderId,
            @PathVariable Long receiverId,
            @PathVariable String content
    ) {
        logger.info("Received request to send message from " + senderId + " to " + receiverId + ": " + content);
        Message message = new Message();
        message.setSender(accountService.findById(senderId));
        message.setReceiver(accountService.findById(receiverId));
        message.setContent(content);
        message.setMessageDate(new Date());

        logger.info("Saving message: " + message);
        messageService.saveMessage(message);
        logger.info("Message saved with ID: " + message.getMessageId());
        return ResponseEntity.ok(message);
    }

    @PutMapping("/{messageId}/status/{status}")
    public ResponseEntity<Message> updateMessageStatus(@PathVariable Long messageId, @PathVariable String status) {
        logger.info("Updating status of message " + messageId + " to " + status);
        messageService.updateMessageStatus(messageId, status);
        return ResponseEntity.ok().build();
    }
}
