package com.nexus.api.data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "`match`") // Using backticks to specify the table name as "match"
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long matchId;

    @ManyToOne
    @JoinColumn(name = "user1_id", referencedColumnName = "account_id")
    private Account user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", referencedColumnName = "account_id")
    private Account user2;

    @Column(name = "match_date", nullable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date matchDate;

    public Match(Long matchId, Account user1, Account user2, Date matchDate) {
        this.matchId = matchId;
        this.user1 = user1;
        this.user2 = user2;
        this.matchDate = matchDate;
    }

    public Match() {

    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Account getUser1() {
        return user1;
    }

    public void setUser1(Account user1) {
        this.user1 = user1;
    }

    public Account getUser2() {
        return user2;
    }

    public void setUser2(Account user2) {
        this.user2 = user2;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }
}
