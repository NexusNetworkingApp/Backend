package main.java.com.nexus.api.business;

import com.nexus.api.data.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
    }

    // You can use photoRepository to perform database operations related to photos.

    // Add methods to interact with photos as needed, e.g., save, find, delete, etc.
}
