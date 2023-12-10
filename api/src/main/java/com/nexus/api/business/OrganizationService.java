package com.nexus.api.business;

import com.nexus.api.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository, AccountRepository accountRepository) {
        this.organizationRepository = organizationRepository;
        this.accountRepository = accountRepository;
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
}
