package test.java.com.nexus.api.business;

import com.nexus.api.data.Message;
import com.nexus.api.data.MessageRepository;
import com.nexus.api.data.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
public class MessageServiceTest {
    
    @Autowired
    private MessageService messageService;

    @MockBean
    private MessageRepository messageRepository;

    private Message message;

    @BeforeEach
    public void setup(){
        Account sender = new Account();
        sender.setAccountId(1L);

        Account receiver = new Account();
        receiver.setAccountId(2L);

        message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent("Hi");
        message.setMessageDate(new Date());
        message.setStatus("SENT");
    
    }

    @Test
    public void testSaveMessage() {
        Mockito.when(messageRepository.save(any(Message.class))).thenReturn(message);

        messageService.saveMessage(message);

        Mockito.verify(messageRepository, Mockito.times(1)).save(message);
        assertEquals("SENT", message.getStatus());
    }

    @Test
    public void testUpdateMessageStatus() {
        Mockito.when(messageRepository.findById(message.getMessageId())).thenReturn(Optional.of(message));

        messageService.updateMessageStatus(message.getMessageId(), "READ");

        Mockito.verify(messageRepository, Mockito.times(1)).save(message);
        assertEquals("READ", message.getStatus());
    }
}
