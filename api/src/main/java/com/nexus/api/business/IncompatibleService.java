package com.nexus.api.business;

import com.nexus.api.data.IncompatibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncompatibleService {

    @Autowired
    public IncompatibleService(IncompatibleRepository incompatibleRepository) {
    }

    // You can use incompatibleRepository to perform database operations related to incompatibilities.

    // Add methods to interact with incompatibilities as needed, e.g., save, find, delete, etc.
}
