package main.java.com.nexus.api.web;

import com.nexus.api.business.*;
import com.nexus.api.data.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/job")
public class JobController {

    private final OrganizationService organizationService;
    private final JobService jobService;

    @Autowired
    public JobController(OrganizationService organizationService, JobService jobService) {
        this.organizationService = organizationService;
        this.jobService = jobService;
    }
    @PostMapping("/organization/post-job")
    public ResponseEntity<String> postJob(@RequestBody Job job) {
        try {
            // Assuming you have a service method to handle job posting logic
            organizationService.postJob(job);
            return ResponseEntity.ok("Job posted successfully!");
        } catch (Exception e) {
            // Print the exception details to the standard error stream
            System.err.println("Error posting job: " + e.getMessage());

            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error posting job: " + e.getMessage());
        }
    }

    @GetMapping("/all-jobs")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }
}
