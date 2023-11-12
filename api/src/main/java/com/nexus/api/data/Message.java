package com.nexus.api.data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "account_id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "account_id")
    private Account receiver;

    @Column(name = "content", length = 500, nullable = false)
    private String content;

    @Column(name = "message_date", nullable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date messageDate;

    public Message(Long messageId, Account sender, Account receiver, String content, Date messageDate) {
        this.messageId = messageId;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.messageDate = messageDate;
    }


    public Message() {

    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }
}
