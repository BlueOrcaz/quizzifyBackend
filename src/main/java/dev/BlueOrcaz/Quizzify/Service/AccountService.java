package dev.BlueOrcaz.Quizzify.Service;

import dev.BlueOrcaz.Quizzify.Model.Account;
import dev.BlueOrcaz.Quizzify.Repository.AccountRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.AbstractNestablePropertyAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class AccountService {
    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public Account createAccount(String username,
                                 String password,
                                 String email,
                                 String dateOfBirth,
                                 String educationalRole,
                                 String role,
                                 ArrayList<ObjectId> createdFlashcardSetsArrayList,
                                 ArrayList<ObjectId> createdFoldersArrayList) {

        String encodedPassword = this.passwordEncoder.encode(password);

        return accountRepository.insert(new Account(username,
                encodedPassword,
                email,
                dateOfBirth,
                educationalRole,
                role,
                createdFlashcardSetsArrayList,
                createdFoldersArrayList));
    }


    public Account updateAccount(ObjectId id, String currentPassword, Account updatedAccount) {
        Account existingAccount = accountRepository.findById(id)
                .orElse(null);

        if (existingAccount == null) {
            return null;
        }


        if (!passwordEncoder.matches(currentPassword, existingAccount.getPassword())) {
            return null;
        }

        existingAccount.setUsername(updatedAccount.getUsername());
        existingAccount.setEmail(updatedAccount.getEmail());
        existingAccount.setEducationalRole(updatedAccount.getEducationalRole());
        existingAccount.setDateOfBirth(updatedAccount.getDateOfBirth());

        String newPassword = updatedAccount.getPassword();
        if (newPassword != null && !newPassword.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(newPassword);
            existingAccount.setPassword(encodedPassword);
        }

        return accountRepository.save(existingAccount);
    }


    public boolean login(String username, String password) {
        Account account = accountRepository.findByUsername(username);
        if(account != null) {
            return passwordEncoder.matches(password, account.getPassword());
        }
        return false;
    }



    public String retrieveRole(String username) {
        Account account = accountRepository.findByUsername(username);
        return account.getRole();
    }

    public ObjectId retrieveId(String userame) {
        Account account = accountRepository.findByUsername(userame);
        return account.getId();
    }

    public List<Account> allAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findAccount(ObjectId id) {
        return accountRepository.findById(id);
    }
}
