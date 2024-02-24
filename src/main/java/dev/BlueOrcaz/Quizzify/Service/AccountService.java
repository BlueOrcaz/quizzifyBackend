package dev.BlueOrcaz.Quizzify.Service;

import dev.BlueOrcaz.Quizzify.Model.Account;
import dev.BlueOrcaz.Quizzify.Model.Flashcards;
import dev.BlueOrcaz.Quizzify.Model.Folder;
import dev.BlueOrcaz.Quizzify.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class AccountService {
    @Autowired
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    //@Autowired
    public Account createAccount(String accountId,
                                 String username,
                                 String password,
                                 String email,
                                 String dateOfBirth,
                                 String educationalRole) {
        return accountRepository.insert(new Account(accountId, username, password, email, dateOfBirth, educationalRole));
    }
}
