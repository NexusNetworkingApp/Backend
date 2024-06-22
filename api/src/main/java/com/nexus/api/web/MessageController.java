package com.nexus.api.web;

import com.nexus.api.business.*;
import com.nexus.api.data.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/message")
public class MessageController {

    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    public MessageController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @GetMapping("/{accountId}/{otherAccountId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long accountId, @PathVariable Long otherAccountId) {
        List<Message> messages = messageService.getMessages(accountId, otherAccountId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/send/{senderId}/{receiverId}/{content}")
    public void sendMessage(
            @PathVariable Long senderId,
            @PathVariable Long receiverId,
            @PathVariable String content
    ) {
        // Implement your logic to save the message
        Message message = new Message();
        // Set other properties of the message as needed
        message.setSender(accountService.findById(senderId)); // Assuming you have an Account entity
        message.setReceiver(accountService.findById(receiverId)); // Assuming you have an Account entity
        message.setContent(content);
        message.setMessageDate(new Date());

        messageService.saveMessage(message);
    }
}
