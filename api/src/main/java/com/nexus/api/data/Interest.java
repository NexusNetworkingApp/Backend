package com.nexus.api.data;

import jakarta.persistence.*;

@Entity
@Table(name = "interest")
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_id")
    private Long interestId;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "individual_id", referencedColumnName = "individual_id")
    private Individual individual;

    public Interest(Long interestId, String description, Individual individual) {
        this.interestId = interestId;
        this.description = description;
        this.individual = individual;
    }

    public Interest() {

    }

    public Long getInterestId() {
        return interestId;
    }

    public void setInterestId(Long interestId) {
        this.interestId = interestId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }
}

