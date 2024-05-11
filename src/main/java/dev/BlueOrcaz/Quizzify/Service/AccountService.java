package dev.BlueOrcaz.Quizzify.Service;

import dev.BlueOrcaz.Quizzify.Model.Account;
import dev.BlueOrcaz.Quizzify.Model.FlashcardSet;
import dev.BlueOrcaz.Quizzify.Model.Folder;
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
                                 ArrayList<String> createdFlashcardSetsArrayList,
                                 ArrayList<String> createdFoldersArrayList
                                 ) { // method to create accounts, as well as encode the password to store in database

            String encodedPassword = this.passwordEncoder.encode(password);


            return accountRepository.insert(new Account(id,
                    username,
                    encodedPassword,
                    email,
                    dateOfBirth,
                    educationalRole,
                    role,
                    createdFlashcardSetsArrayList,
                    createdFoldersArrayList
            ));

    }

    public boolean duplicateUsername(String username) {
        return accountRepository.findByUsername(username) != null;
    }

    public void addFlashcardSetToAccount(ObjectId accountId, FlashcardSet flashcardSet) { // add a flashcard set to the account by getting the specific id each time a flashcard set is made
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found")); // find account
        account.getCreatedFlashcardSetsArrayList().add(flashcardSet.getId().toString()); // get the id
        accountRepository.save(account); // update account repo
    }

    public void addFolderSetToAccount(ObjectId accountId, Folder folder) { // add a folder to the account by getting specific id each time a flashcard set is made
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found")); // find account
        account.getCreatedFoldersArrayList().add(folder.getId().toString()); // get the id
        accountRepository.save(account);// update account repo
    }




    public Account updateAccount(ObjectId id, String currentPassword, Account updatedAccount) { // update account details based off of the requested id.
        Account existingAccount = accountRepository.findById(id)
                .orElse(null); // if there isnt anything return null

        if (existingAccount == null) { // if no account is found then return null
            return null;
        }


        if (!passwordEncoder.matches(currentPassword, existingAccount.getPassword())) { // if doesnt match then it wont update account
            return null;
        }

        existingAccount.setUsername(updatedAccount.getUsername());
        existingAccount.setEmail(updatedAccount.getEmail());
        existingAccount.setEducationalRole(updatedAccount.getEducationalRole());
        existingAccount.setDateOfBirth(updatedAccount.getDateOfBirth());

        String newPassword = updatedAccount.getPassword();
        if (newPassword != null && !newPassword.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(newPassword); // encode passsword in a hash
            existingAccount.setPassword(encodedPassword); // update password hash in database
        }

        return accountRepository.save(existingAccount); // update the account in database with the new details
    }

    public boolean login(String username, String password) { // allows user to login if they find account details and password matches
        Account account = accountRepository.findByUsername(username);
        if(account != null) {
            return passwordEncoder.matches(password, account.getPassword()); // check if the password matches
        }
        return false;
    }



    public String retrieveRole(String username) { // retrieve the user's role based off of their username
        Account account = accountRepository.findByUsername(username);
        return account.getRole();
    }

    public ObjectId retrieveId(String username) { // retrieve the user's id based off of their username
        Account account = accountRepository.findByUsername(username);
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
