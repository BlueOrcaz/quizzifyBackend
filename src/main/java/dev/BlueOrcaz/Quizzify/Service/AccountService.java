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
    // stores all the methods in the backend.
    @Autowired
    private final AccountRepository accountRepository; // account repository

    @Autowired
    private PasswordEncoder passwordEncoder; // spring security to hash passwords

    public AccountService(AccountRepository accountRepository) { // constructor
        this.accountRepository = accountRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public Account createAccount(ObjectId id,
                                 String username,
                                 String password,
                                 String email,
                                 String dateOfBirth,
                                 String educationalRole,
                                 String role,
                                 ArrayList<ObjectId> createdFlashcardSetsArrayList,
                                 ArrayList<ObjectId> createdFoldersArrayList) { // method to create accounts, as well as encode the password to store in database

        String encodedPassword = this.passwordEncoder.encode(password);

        return accountRepository.insert(new Account(id,
                username,
                encodedPassword,
                email,
                dateOfBirth,
                educationalRole,
                role,
                createdFlashcardSetsArrayList,
                createdFoldersArrayList));
    }


    public Account updateAccount(ObjectId id, String currentPassword, Account updatedAccount) { // update account details based off of the requested id.
        Account existingAccount = accountRepository.findById(id)
                .orElse(null); // if there isnt anything return null

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

        return accountRepository.save(existingAccount); // update the account in database with the new details
    }


    public boolean login(String username, String password) { // allows user to login if they find account details and password matches
        Account account = accountRepository.findByUsername(username);
        if(account != null) {
            return passwordEncoder.matches(password, account.getPassword());
        }
        return false;
    }



    public String retrieveRole(String username) { // retrieve the user's role based off of their username
        Account account = accountRepository.findByUsername(username);
        return account.getRole();
    }

    public ObjectId retrieveId(String userame) { // retrieve the user's id based off of their username
        Account account = accountRepository.findByUsername(userame);
        return account.getId();
    }

    public List<Account> allAccounts() {
        return accountRepository.findAll();
    } // returns all accounts in a list

    public Optional<Account> findAccount(ObjectId id) { // returns a specific account obj based off of the object id
        return accountRepository.findById(id);
    }

    public boolean deleteAccount(ObjectId accountId, String currentPassword) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (!passwordEncoder.matches(currentPassword, account.getPassword())) { // check if the password matches
                return false; // password doesn't match, return false
            }

            accountRepository.delete(account); // delete the account
            return true;
        } else {
            return false; // account not found, return false
        }
    }

}
