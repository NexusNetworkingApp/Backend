package main.java.com.nexus.api.business;

import com.nexus.api.data.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreferenceService {

    @Autowired
    public PreferenceService(PreferenceRepository preferenceRepository) {
    }

    // You can use preferenceRepository to perform database operations related to preferences.

    // Add methods to interact with preferences as needed, e.g., save, find, delete, etc.
}
