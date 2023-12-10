package com.nexus.api.business;

import com.nexus.api.data.Account;
import com.nexus.api.data.AccountType;
import com.nexus.api.data.AccountRepository;
import com.nexus.api.data.Individual;
import com.nexus.api.data.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class IndividualService {
    private final IndividualRepository individualRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public IndividualService(IndividualRepository individualRepository, AccountRepository accountRepository) {
        this.individualRepository = individualRepository;
        this.accountRepository = accountRepository;
    }

    public Individual createIndividual (Individual individual) {

        //Save Individual
        Individual newIndividual = individualRepository.save(individual);

        //Save Account for Individual
        Account account = new Account();
        account.setAccountType(AccountType.INDIVIDUAL);
        account.setIndividual(newIndividual);

        // Save the Account
        accountRepository.save(account);


        return newIndividual;
    }

    public void deleteIndividual(Long individualId) {
        // Delete Individual and associated Account
        individualRepository.deleteById(individualId);
    }

    public Boolean verifyIndividual(String email, String password) {
        Optional<Individual> individualOptional = individualRepository.findByEmailAndPasswordHash(email, password);
        return individualOptional.isPresent();
    }

}
