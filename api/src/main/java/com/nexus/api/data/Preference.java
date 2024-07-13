package main.java.com.nexus.api.data;

import jakarta.persistence.*;

@Entity
@Table(name = "preference")
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private Long preferenceId;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @Column(name = "radius", nullable = false)
    private int radius;

    public Preference(Long preferenceId, Account account, int radius) {
        this.preferenceId = preferenceId;
        this.account = account;
        this.radius = radius;
    }

    public Preference() {

    }

    public Long getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(Long preferenceId) {
        this.preferenceId = preferenceId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
