package com.nexus.api.business;

import com.nexus.api.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final LikeRepository likeRepository;
    private final MatchRepository matchRepository;
    private final ZipCodeService zipCodeService;

    @Autowired
    public AccountService(AccountRepository accountRepository, ZipCodeService zipCodeService, LikeRepository likeRepository, MatchRepository matchRepository) {
        this.accountRepository = accountRepository;
        this.zipCodeService = zipCodeService;
        this.likeRepository = likeRepository;
        this.matchRepository = matchRepository;
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
        // System.out.println("Finding zip codes within radius for zip code: " + zipCode + " and distance: " + distance);
        return zipCodeService.getZipCodesWithinRadius(String.valueOf(zipCode), distance);
    }

    private List<Long> getLikedOrMatchedAccountIds(Long loggedInAccountId) {
        // Get accounts that have been liked or matched by the logged-in user
        List<Long> relatedLikedAccountIds = likeRepository.findRelatedByAccountId(loggedInAccountId)
                .stream()
                .flatMap(like -> Stream.of(like.getSender().getAccountId(), like.getReceiver().getAccountId()))
                .distinct()
                .collect(Collectors.toList());
        List<Long> relatedMatchedAccountIds = matchRepository.findRelatedMatchedAccount(loggedInAccountId)
                .stream()
                .flatMap(match -> Stream.of(match.getUser1().getAccountId(), match.getUser2().getAccountId()))
                .distinct()
                .collect(Collectors.toList());

        relatedLikedAccountIds.addAll(relatedMatchedAccountIds);
        // TODO: Add logic to get matched accounts if you have a match entity

        return relatedLikedAccountIds;
    }

    public List<Account> findAccountsByZipCodes(List<String> zipCodes, Long loggedInAccountId) {
        List<Account> allAccounts = accountRepository.findAll();
        List<Long> excludedAccountIds = getLikedOrMatchedAccountIds(loggedInAccountId);

        return allAccounts.stream()
                .filter(account -> {
                    int accountZip = (account.getAccountType() == AccountType.INDIVIDUAL) ?
                            account.getIndividual().getLocation() :
                            account.getOrganization().getLocation();
                    return zipCodes.contains(String.valueOf(accountZip)) && !excludedAccountIds.contains(account.getAccountId());
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
