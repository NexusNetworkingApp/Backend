package com.nexus.api.data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "app_feedback")
public class AppFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long feedbackId;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Column(name = "feedback_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feedbackDate;

    public AppFeedback(Long feedbackId, Account account, String description, Date feedbackDate) {
        this.feedbackId = feedbackId;
        this.account = account;
        this.description = description;
        this.feedbackDate = feedbackDate;
    }

    public AppFeedback() {

    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}

