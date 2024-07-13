package main.java.com.nexus.api.business;

import com.nexus.api.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final AccountRepository accountRepository;
    private final JobRepository jobRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository, AccountRepository accountRepository, JobRepository jobRepository) {
        this.organizationRepository = organizationRepository;
        this.accountRepository = accountRepository;
        this.jobRepository = jobRepository;
    }

    public Organization createOrganization (Organization organization) {

        //Save Individual
        Organization newOrganization = organizationRepository.save(organization);

        //Save Account for Individual
        Account account = new Account();
        account.setAccountType(AccountType.ORGANIZATION);
        account.setOrganization(newOrganization);

        // Save the Account
        accountRepository.save(account);


        return newOrganization;
    }

    public void deleteOrganization(Long organizationId) {
        organizationRepository.deleteById(organizationId);
    }

    public Boolean verifyOrganization(String email, String password) {
        Optional<Organization> organizationOptional = organizationRepository.findByEmailAndPasswordHash(email, password);
        return organizationOptional.isPresent();
    }

    public void postJob(Job job) {
        jobRepository.save(job);
    }
}
