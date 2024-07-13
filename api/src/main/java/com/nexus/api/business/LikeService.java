package main.java.com.nexus.api.business;

import com.nexus.api.data.*;
import org.hibernate.boot.model.relational.InitCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final IncompatibleRepository incompatibleRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository, IncompatibleRepository incompatibleRepository, MessageRepository messageRepository) {
        this.likeRepository = likeRepository;
        this.incompatibleRepository = incompatibleRepository;
        this.messageRepository = messageRepository;
    }

    public void createLike(Like like) {
        try {
            System.out.println("Saving like: " + like);

            likeRepository.save(like);

            // Make the two users un-discoverable to each other
            Incompatible newIncompatible = new Incompatible();
            newIncompatible.setUser1(like.getReceiver());
            newIncompatible.setUser2(like.getSender());
            newIncompatible.setReason("Like");

            System.out.println("Saving incompatible entry: " + newIncompatible);
            incompatibleRepository.save(newIncompatible);

            // Save initial message sent with like
            Message newMessage = new Message();
            newMessage.setSender(like.getSender());
            newMessage.setReceiver(like.getReceiver());
            newMessage.setContent(like.getLikeMessage());
            newMessage.setMessageDate(like.getLikeDate());
            newMessage.setStatus("SENT");
            newMessage.setVersion(0);

            System.out.println("Saving message: " + newMessage);
            messageRepository.save(newMessage);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public List<Like> getLikesByReceiverId(Long receiverId) {
        return likeRepository.findByReceiverAccountId(receiverId);

    }

}
