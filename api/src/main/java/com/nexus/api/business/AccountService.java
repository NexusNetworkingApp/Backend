package com.nexus.api.business;

import com.nexus.api.data.Account;
import com.nexus.api.data.AccountRepository;
import com.nexus.api.data.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nexus.api.data.*;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findAccountByTypeAndEmail(AccountType accountType, String email) {
        if (accountType == AccountType.INDIVIDUAL) {
            return accountRepository.findByAccountTypeAndEmailForIndividual(accountType, email);
        } else if (accountType == AccountType.ORGANIZATION) {
            return accountRepository.findByAccountTypeAndEmailForOrganization(accountType, email);
        } else {
            // Handle unsupported account type
            return null;
        }
    }

    public Individual findIndividualByAccountId(Long accountId) {
        return accountRepository.findIndividualByAccountId(accountId);
    }

    public Organization findOrganizationByAccountId(Long accountId) {
        return accountRepository.findOrganizationByAccountId(accountId);
    }
}
