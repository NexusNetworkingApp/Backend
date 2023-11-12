package com.nexus.api.business;

import com.nexus.api.data.AppFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppFeedbackService {

    @Autowired
    public AppFeedbackService(AppFeedbackRepository appFeedbackRepository) {
    }

    // You can use appFeedbackRepository to perform database operations related to app feedback.

    // Add methods to interact with app feedback as needed, e.g., save, find, delete, etc.
}
