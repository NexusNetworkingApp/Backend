package com.nexus.api.business;

import com.nexus.api.data.Account;
import com.nexus.api.data.AccountRepository;
import com.nexus.api.data.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nexus.api.data.*;

import java.util.List;
import java.util.Random;

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

    public Account getRandomAccount(Long excludedAccountId) {
        // Logic to get a random account excluding the provided accountId
        List<Account> accounts = accountRepository.findAllByAccountIdNot(excludedAccountId);

        if (accounts.isEmpty()) {
            // Handle the case where no suitable account is found
            return null;
        }

        // Select a random account from the list
        Random random = new Random();
        int randomIndex = random.nextInt(accounts.size());
        return accounts.get(randomIndex);
    }

    public Account getRandomIndividualAccount() {
        List<Account> individualAccounts = accountRepository.findByAccountType(AccountType.INDIVIDUAL);

        if (individualAccounts.isEmpty()) {
            // Handle the case where no individual account is found
            return null;
        }

        // Select a random individual account from the list
        Random random = new Random();
        int randomIndex = random.nextInt(individualAccounts.size());
        return individualAccounts.get(randomIndex);
    }

    public Account findById(Long senderId) {
        return accountRepository.findById(senderId).orElse(null);
    }

    public List<Account> getStandouts() {
        // Implement logic to retrieve top individual accounts by likes received
        // This might involve querying the database or another data source
        // Adjust the logic based on your application's needs
        return accountRepository.findTop5ByAccountTypeOrderByReceivedLikesDesc(AccountType.INDIVIDUAL);
    }
}
