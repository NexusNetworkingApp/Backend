package com.nexus.api.web;

import com.nexus.api.business.AccountService;
import com.nexus.api.business.IndividualService;
import com.nexus.api.business.OrganizationService;
import com.nexus.api.data.Individual;
import com.nexus.api.data.Organization;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/account")
public class AccountController {

    private final IndividualService individualService;
    private final OrganizationService organizationService;
    private final AccountService accountService;

    @Autowired
    public AccountController(IndividualService individualService, OrganizationService organizationService, AccountService accountService) {
        this.individualService = individualService;
        this.organizationService = organizationService;
        this.accountService = accountService;
    }

    // Individual account endpoints

    @PostMapping("/create-individual")
    public ResponseEntity<String> createIndividual(@RequestBody Individual individual) {
        try {
            Individual newIndividual = individualService.createIndividual(individual);
            return ResponseEntity.status(HttpStatus.CREATED).body("Individual created successfully. Individual ID: " + newIndividual.getIndividualId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating individual: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{individualId}")
    public ResponseEntity<String> deleteIndividual(@PathVariable Long individualId) {
        try {
            individualService.deleteIndividual(individualId);
            return ResponseEntity.status(HttpStatus.OK).body("Individual deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error deleting individual: " + e.getMessage());
        }
    }

    @GetMapping("/login-individual/{email}/{password}")
    public ResponseEntity<Boolean> loginIndividual(@PathVariable String email, @PathVariable String password) {
        try {
            Boolean verificationStatus = individualService.verifyIndividual(email, password);
            return ResponseEntity.ok(verificationStatus);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }




    // Organization account endpoints

    @PostMapping("/create-organization")
    public ResponseEntity<String> createOrganization(@RequestBody Organization organization) {
        try {
            Organization newOrganization = organizationService.createOrganization(organization);
            return ResponseEntity.status(HttpStatus.CREATED).body("Individual created successfully. Individual ID: " + newOrganization.getOrganizationId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating organization: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{organizationId}")
    public ResponseEntity<String> deleteOrganization(@PathVariable Long organizationId) {
        try {
            organizationService.deleteOrganization(organizationId);
            return ResponseEntity.status(HttpStatus.OK).body("Organization deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error deleting organization: " + e.getMessage());
        }
    }

    @GetMapping("/login-organization/{email}/{password}")
    public ResponseEntity<Boolean> loginOrganization(@PathVariable String email, @PathVariable String password) {
        try {
            Boolean verificationStatus = organizationService.verifyOrganization(email, password);
            return ResponseEntity.ok(verificationStatus);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }


}
