package com.nexus.api.business;

import com.nexus.api.data.Job;
import com.nexus.api.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // You can use jobRepository to perform database operations related to jobs.

    // Add methods to interact with jobs as needed, e.g., save, find, delete, etc.
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}

