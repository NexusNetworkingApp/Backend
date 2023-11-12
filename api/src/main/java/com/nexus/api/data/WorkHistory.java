package com.nexus.api.data;

import jakarta.persistence.*;

@Entity
@Table(name = "work_history")
public class WorkHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_history_id")
    private Long workHistoryId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "individual_id", referencedColumnName = "individual_id")
    private Individual individual;

    public WorkHistory(Long workHistoryId, String title, String description, Individual individual) {
        this.workHistoryId = workHistoryId;
        this.title = title;
        this.description = description;
        this.individual = individual;
    }

    public WorkHistory() {

    }

    public Long getWorkHistoryId() {
        return workHistoryId;
    }

    public void setWorkHistoryId(Long workHistoryId) {
        this.workHistoryId = workHistoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
