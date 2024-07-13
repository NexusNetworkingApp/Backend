package main.java.com.nexus.api.business;

import com.nexus.api.data.WorkHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkHistoryService {

    @Autowired
    public WorkHistoryService(WorkHistoryRepository workHistoryRepository) {
    }

    // You can use workHistoryRepository to perform database operations related to work history entries.

    // Add methods to interact with work history entries as needed, e.g., save, find, delete, etc.
}

