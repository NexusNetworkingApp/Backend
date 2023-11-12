package com.nexus.api.business;

import com.nexus.api.data.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
    }

    // You can use organizationRepository to perform database operations related to organizations.

    // Add methods to interact with organizations as needed, e.g., save, find, delete, etc.
}
