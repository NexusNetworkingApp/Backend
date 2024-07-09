package com.nexus.api.business;

import com.nexus.api.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ZipCodeService zipCodeService;

    @Autowired
    public AccountService(AccountRepository accountRepository, ZipCodeService zipCodeService) {
        this.accountRepository = accountRepository;
        this.zipCodeService = zipCodeService;
    }

    public Account findAccountByTypeAndEmail(AccountType accountType, String email) {
        if (accountType == AccountType.INDIVIDUAL) {
            return accountRepository.findByAccountTypeAndEmailForIndividual(accountType, email);
        } else if (accountType == AccountType.ORGANIZATION) {
            return accountRepository.findByAccountTypeAndEmailForOrganization(accountType, email);
        } else {
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
        List<Account> accounts = accountRepository.findAllByAccountIdNot(excludedAccountId);

        if (accounts.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(accounts.size());
        return accounts.get(randomIndex);
    }

    public Account getRandomIndividualAccount() {
        List<Account> individualAccounts = accountRepository.findByAccountType(AccountType.INDIVIDUAL);

        if (individualAccounts.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(individualAccounts.size());
        return individualAccounts.get(randomIndex);
    }

    public Account findById(Long senderId) {
        return accountRepository.findById(senderId).orElse(null);
    }

    public List<Account> getStandouts() {
        return accountRepository.findTop5ByAccountTypeOrderByReceivedLikesDesc(AccountType.INDIVIDUAL);
    }

    public List<String> findZipCodesWithinRadius(int zipCode, int distance) {
        System.out.println("Finding zip codes within radius for zip code: " + zipCode + " and distance: " + distance);
        return zipCodeService.getZipCodesWithinRadius(String.valueOf(zipCode), distance);
    }

    public List<Account> findAccountsByZipCodes(List<String> zipCodes) {
        System.out.println("Finding accounts with zip codes: " + zipCodes);
        List<Account> allAccounts = accountRepository.findAll();
        return allAccounts.stream()
                .filter(account -> {
                    int accountZip = (account.getAccountType() == AccountType.INDIVIDUAL) ?
                            account.getIndividual().getLocation() :
                            account.getOrganization().getLocation();
                    return zipCodes.contains(String.valueOf(accountZip));
                })
                .collect(Collectors.toList());
    }

    public Account getRandomAccountFromList(List<Account> accounts) {
        if (accounts.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(accounts.size());
        return accounts.get(randomIndex);
    }
}
