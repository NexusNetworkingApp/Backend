package com.nexus.api.business;

import com.nexus.api.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    public JobService(JobRepository jobRepository) {
    }

    // You can use jobRepository to perform database operations related to jobs.

    // Add methods to interact with jobs as needed, e.g., save, find, delete, etc.
}

