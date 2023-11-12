package com.nexus.api.data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "job_application")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "individual_id", referencedColumnName = "individual_id")
    private Individual individual;

    @Column(name = "application_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicationDate;

    public JobApplication(Long applicationId, Job job, Individual individual, Date applicationDate) {
        this.applicationId = applicationId;
        this.job = job;
        this.individual = individual;
        this.applicationDate = applicationDate;
    }

    public JobApplication() {
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }
}
