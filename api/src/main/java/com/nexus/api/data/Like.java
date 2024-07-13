package main.java.com.nexus.api.data;

import jakarta.persistence.*;
import java.util.Date;


@Entity
@Table(name = "`like`") // Using backticks to specify the table name as "like"
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "account_id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "account_id")
    private Account receiver;

    @Column(name = "like_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date likeDate;

    @Column(name = "like_message", length = 500)
    private String likeMessage;

    @Enumerated(EnumType.STRING)
    @Column(name = "prompt", nullable = false)
    private LikePrompt prompt;

    public Like(Long likeId, Account sender, Account receiver, Date likeDate, String likeMessage, LikePrompt prompt) {
        this.likeId = likeId;
        this.sender = sender;
        this.receiver = receiver;
        this.likeDate = likeDate;
        this.likeMessage = likeMessage;
        this.prompt = prompt;
    }

    public Like() {

    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }

    public String getLikeMessage() {
        return likeMessage;
    }

    public void setLikeMessage(String likeMessage) {
        this.likeMessage = likeMessage;
    }

    public LikePrompt getPrompt() {
        return prompt;
    }

    public void setPrompt(LikePrompt prompt) {
        this.prompt = prompt;
    }
}
