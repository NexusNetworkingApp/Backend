package com.nexus.api.data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "organization_id")
    private Organization organization;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private JobType type;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "post_duration", nullable = false)
    private int postDuration;

    @Column(name = "post_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;

    public Job(Long jobId, Organization organization, String title, String description, JobType type, Date startDate, Date endDate, int postDuration, Date postDate) {
        this.jobId = jobId;
        this.organization = organization;
        this.title = title;
        this.description = description;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.postDuration = postDuration;
        this.postDate = postDate;
    }

    public Job() {

    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPostDuration() {
        return postDuration;
    }

    public void setPostDuration(int postDuration) {
        this.postDuration = postDuration;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
