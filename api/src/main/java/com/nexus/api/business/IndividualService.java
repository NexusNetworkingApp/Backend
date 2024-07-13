package main.java.com.nexus.api.business;

import com.nexus.api.data.Account;
import com.nexus.api.data.AccountType;
import com.nexus.api.data.AccountRepository;
import com.nexus.api.data.Individual;
import com.nexus.api.data.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;


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

    public Individual getRandomIndividual() {
        List<Individual> individuals = individualRepository.findAll();

        if (individuals.isEmpty()) {
            // Handle the case where no suitable individual is found
            return null;
        }

        // Select a random account from the list
        Random random = new Random();
        int randomIndex = random.nextInt(individuals.size());
        return individuals.get(randomIndex);
    }
}
