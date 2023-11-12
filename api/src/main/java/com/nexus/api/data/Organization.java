package com.nexus.api.data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Column(name = "founded_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date foundedDate;

    @Column(name = "industry", nullable = false)
    private String industry;

    @Column(name = "receive_notifications", nullable = false)
    private boolean receiveNotifications;

    @Column(name = "biography")
    private String biography;

    @Column(name = "last_active", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActive;

    @Column(name = "verified", nullable = false)
    private boolean verified;

    @Column(name = "location", nullable = false)
    private int location;

    public Organization(Long organizationId, String email, String passwordHash, String organizationName, Date foundedDate, String industry, boolean receiveNotifications, String biography, Date lastActive, boolean verified, int location) {
        this.organizationId = organizationId;
        this.email = email;
        this.passwordHash = passwordHash;
        this.organizationName = organizationName;
        this.foundedDate = foundedDate;
        this.industry = industry;
        this.receiveNotifications = receiveNotifications;
        this.biography = biography;
        this.lastActive = lastActive;
        this.verified = verified;
        this.location = location;
    }

    public Organization() {

    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Date getFoundedDate() {
        return foundedDate;
    }

    public void setFoundedDate(Date foundedDate) {
        this.foundedDate = foundedDate;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public boolean isReceiveNotifications() {
        return receiveNotifications;
    }

    public void setReceiveNotifications(boolean receiveNotifications) {
        this.receiveNotifications = receiveNotifications;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
