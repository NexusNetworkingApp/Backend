package com.nexus.api.data;

import jakarta.persistence.*;


@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "individual_id", referencedColumnName = "individual_id")
    private Individual individual;

    public Skill(Long id, String description, Individual individual) {
        this.id = id;
        this.description = description;
        this.individual = individual;
    }

    public Skill() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
