package com.nexus.api.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nexus.api.data.SkillRepository;

@Service
public class SkillService {

    @Autowired
    public SkillService(SkillRepository skillRepository) {
    }

    // You can use skillRepository to perform database operations related to skills.

    // Add methods to interact with skills as needed, e.g., save, find, delete, etc.
}
