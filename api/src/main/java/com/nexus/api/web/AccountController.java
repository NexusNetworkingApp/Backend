package com.nexus.api.web;

import com.nexus.api.business.AccountService;
import com.nexus.api.business.IndividualService;
import com.nexus.api.business.OrganizationService;
import com.nexus.api.data.Individual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

    private final IndividualService individualService;

    @Autowired
    public AccountController(IndividualService individualService, OrganizationService organizationService) {
        this.individualService = individualService;
    }

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

}
