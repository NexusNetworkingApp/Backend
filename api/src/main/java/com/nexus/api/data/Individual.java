package com.nexus.api.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "individual")
public class Individual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "individual_id")
    private Long individualId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "receive_notifications", nullable = false)
    private boolean receiveNotifications;

    @Column(name = "biography")
    private String biography;

    @Column(name = "last_active", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActive;

    @Column(name = "location", nullable = false)
    private int location;

    public Individual(Long individualId, String email, String passwordHash, String firstName, String lastName, Date dateOfBirth, String gender, boolean receiveNotifications, String biography, Date lastActive, int location) {
        this.individualId = individualId;
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.receiveNotifications = receiveNotifications;
        this.biography = biography;
        this.lastActive = lastActive;
        this.location = location;
    }

    public Individual() {

    }

    public Long getIndividualId() {
        return individualId;
    }

    public void setIndividualId(Long individualId) {
        this.individualId = individualId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
