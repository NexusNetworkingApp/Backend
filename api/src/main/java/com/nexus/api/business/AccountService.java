package com.nexus.api.business;

import com.nexus.api.data.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    public AccountService(AccountRepository accountRepository) {
    }

    // You can use accountRepository to perform database operations related to accounts.

    // Add methods to interact with accounts as needed, e.g., save, find, delete, etc.
}
