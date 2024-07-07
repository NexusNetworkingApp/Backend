package com.nexus.api.web;

import com.nexus.api.business.AccountService;
import com.nexus.api.business.MessageService;
import com.nexus.api.data.Account;
import com.nexus.api.data.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.logging.Logger;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final AccountService accountService;
    private static final Logger logger = Logger.getLogger(WebSocketController.class.getName());

    @Autowired
    public WebSocketController(SimpMessagingTemplate messagingTemplate, MessageService messageService, AccountService accountService){
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
        this.accountService = accountService;
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(Message message) {
        logger.info("Received WebSocket message from " + message.getSender().getAccountId() + " to " + message.getReceiver().getAccountId() + ": " + message.getContent());

        // Validate and retrieve sender and receiver from the database
        Account sender = accountService.findById(message.getSender().getAccountId());
        Account receiver = accountService.findById(message.getReceiver().getAccountId());

        if (sender == null || receiver == null) {
            logger.warning("Sender or Receiver not found");
            return;
        }

        // Set the sender and receiver with the actual entities retrieved
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setMessageDate(new Date());
        message.setStatus("SENT");

        logger.info("Saving message: " + message);
        messageService.saveMessage(message);
        logger.info("Message saved with ID: " + message.getMessageId());

        // Send the message to the recipient's WebSocket queue
        messagingTemplate.convertAndSendToUser(
                message.getReceiver().getAccountId().toString(),
                "/queue/messages",
                message
        );
    }
}
