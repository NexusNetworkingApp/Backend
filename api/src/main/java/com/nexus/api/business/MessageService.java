package com.nexus.api.business;

import com.nexus.api.data.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    public MessageService(MessageRepository messageRepository) {
    }

    // You can use messageRepository to perform database operations related to messages.

    // Add methods to interact with messages as needed, e.g., save, find, delete, etc.
}
