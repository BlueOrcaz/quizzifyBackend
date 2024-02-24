package dev.BlueOrcaz.Quizzify.Service;

import dev.BlueOrcaz.Quizzify.Model.Account;
import dev.BlueOrcaz.Quizzify.Repository.AccountRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class AccountService {
    @Autowired
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account createAccount(String username,
                                 String password,
                                 String email,
                                 String dateOfBirth,
                                 String educationalRole,
                                 boolean admin,
                                 ArrayList<ObjectId> createdFlashcardSetsArrayList,
                                 ArrayList<ObjectId> createdFoldersArrayList) {
        return accountRepository.insert(new Account(username,
                password,
                email,
                dateOfBirth,
                educationalRole,
                admin,
                createdFlashcardSetsArrayList,
                createdFoldersArrayList));
    }

    public List<Account> allAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findAccount(ObjectId id) {
        return accountRepository.findById(id);
    }
}
