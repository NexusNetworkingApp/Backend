package com.nexus.api.business;

import com.nexus.api.data.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationService {

    @Autowired
    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
    }

    // You can use jobApplicationRepository to perform database operations related to job applications.

    // Add methods to interact with job applications as needed, e.g., save, find, delete, etc.
}
