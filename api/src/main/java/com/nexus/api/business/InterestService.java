package com.nexus.api.business;

import com.nexus.api.data.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestService {

    @Autowired
    public InterestService(InterestRepository interestRepository) {
    }

    // You can use interestRepository to perform database operations related to interests.

    // Add methods to interact with interests as needed, e.g., save, find, delete, etc.
}
