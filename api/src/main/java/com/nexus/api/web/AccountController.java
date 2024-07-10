package com.nexus.api.web;

import com.nexus.api.business.*;
import com.nexus.api.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/account")
public class AccountController {

    private final IndividualService individualService;
    private final OrganizationService organizationService;
    private final AccountService accountService;
    private final LikeService likeService;
    private final MatchService matchService;
    private final MessageService messageService;
    private final JobService jobService;

    @Autowired
    public AccountController(IndividualService individualService, OrganizationService organizationService, AccountService accountService, LikeService likeService, MatchService matchService, MessageService messageService, JobService jobService) {
        this.individualService = individualService;
        this.organizationService = organizationService;
        this.accountService = accountService;
        this.likeService = likeService;
        this.matchService = matchService;
        this.messageService = messageService;
        this.jobService = jobService;
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

    @GetMapping("/individual-info/{accountId}")
    public ResponseEntity<Individual> getIndividualInfo(@PathVariable Long accountId) {
        try {
            Individual individual = accountService.findIndividualByAccountId(accountId);

            if (individual != null) {
                return new ResponseEntity<>(individual, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/individual-discover/{accountId}")
    public ResponseEntity<Account> getRandomAccount(@PathVariable Long accountId) {
        try {
            // Get a random account that doesn't have the same ID as the provided accountId
            Account randomAccount = accountService.getRandomAccount(accountId);

            return ResponseEntity.ok(randomAccount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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

    @GetMapping("/organization-info/{accountId}")
    public ResponseEntity<Organization> getOrganizationInfo(@PathVariable Long accountId) {
        try {
            Organization organization = accountService.findOrganizationByAccountId(accountId);

            if (organization != null) {
                return new ResponseEntity<>(organization, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/organization-discover")
    public ResponseEntity<Account> getRandomIndividualAccount() {
        try {
            // Get a random account w/ individual accountType
            Account randomIndividualAccount = accountService.getRandomIndividualAccount();

            return ResponseEntity.ok(randomIndividualAccount);
        } catch (Exception e) {
            // Print the exception details to the console
            System.out.println("Error while fetching random individual account");
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // General account endpoints
    @GetMapping("/info/{accountType}/{email}")
    public ResponseEntity<Account> getAccountInfo(@PathVariable String accountType, @PathVariable String email) {
        try {
            // Convert the account type String to the corresponding enum value
            AccountType type = AccountType.valueOf(accountType.toUpperCase());

            // Call the service to retrieve the account information
            Account account = accountService.findAccountByTypeAndEmail(type, email);

            if (account != null) {
                return new ResponseEntity<>(account, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            // Handle invalid account type
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle other exceptions
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/standouts")
    public ResponseEntity<List<Account>> getStandouts() {
        try {
            // Get top individual accounts by likes received
            List<Account> topIndividualAccounts = accountService.getStandouts();

            return ResponseEntity.ok(topIndividualAccounts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/discover")
    public ResponseEntity<Account> discoverAccounts(
            @RequestParam int zipCode,
            @RequestParam int distance,
            @RequestParam Long loggedInAccountId) {
        try {
            // System.out.println("Discover endpoint hit with zipCode: " + zipCode + ", distance: " + distance + ", loggedInAccountId: " + loggedInAccountId);

            // Fetching the zip codes within the radius
            List<String> zipCodesWithinRadius = accountService.findZipCodesWithinRadius(zipCode, distance);
            // System.out.println("Zip codes within radius: " + zipCodesWithinRadius);

            // Fetching eligible accounts
            List<Account> eligibleAccounts = accountService.findAccountsByZipCodes(zipCodesWithinRadius, loggedInAccountId);
            // System.out.println("Eligible accounts: " + eligibleAccounts);

            if (!eligibleAccounts.isEmpty()) {
                // Returning a random eligible account
                Account randomAccount = accountService.getRandomAccountFromList(eligibleAccounts);
                // System.out.println("Returning random eligible account: " + randomAccount);
                return ResponseEntity.ok(randomAccount);
            } else {
                // System.out.println("No eligible accounts found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
