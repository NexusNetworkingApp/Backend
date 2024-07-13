package main.java.com.nexus.api.data;

import jakarta.persistence.*;

@Entity
@Table(name = "incompatible")
public class Incompatible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incompatible_id")
    private Long incompatibleId;

    @ManyToOne
    @JoinColumn(name = "user1_id", referencedColumnName = "account_id")
    private Account user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", referencedColumnName = "account_id")
    private Account user2;

    @Column(name = "reason", length = 500)
    private String reason;

    public Incompatible(Long incompatibleId, Account user1, Account user2, String reason) {
        this.incompatibleId = incompatibleId;
        this.user1 = user1;
        this.user2 = user2;
        this.reason = reason;
    }

    public Incompatible() {
    }

    public Long getIncompatibleId() {
        return incompatibleId;
    }

    public void setIncompatibleId(Long incompatibleId) {
        this.incompatibleId = incompatibleId;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
