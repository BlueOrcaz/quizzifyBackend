package dev.BlueOrcaz.Quizzify.Controller;

import dev.BlueOrcaz.Quizzify.Model.Account;
import dev.BlueOrcaz.Quizzify.Service.AccountService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController // tells spring boot that it is a controller class
@RequestMapping("/api/v1/accounts") // api link - eg: http:localhost:8080/api/v1/accounts/*inserthere*/
@CrossOrigin(origins = "*") // open to access

public class AccountController {
    @Autowired // automatically wire required dependencies in classes
    private final AccountService accountService; // call for accountservice class to utilise its methods

    public AccountController(AccountService accountService) { // constructor
        this.accountService = accountService;
    }

    @PostMapping("/createAccount") // post request that the end user uses to create account (/api/v1/accounts/createAccount)
    public ResponseEntity<Account> createAccount(@RequestBody Account account) { // request body consists of all account details in account object
        Account createdAccount = accountService.createAccount(account.getId(), // create account method from accountservice
                account.getUsername(),
                account.getPassword(),
                account.getEmail(),
                account.getDateOfBirth(),
                account.getEducationalRole(),
                account.getRole(),
                account.getCreatedFlashcardSetsArrayList(),
                account.getCreatedFoldersArrayList());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount); // returns the account details
    }


    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE) // post request that the end user uses to log in with account details
    public ResponseEntity<String> loginAccount(@RequestBody Account account) {
        boolean isAuthenticated = accountService.login(account.getUsername(), account.getPassword()); // checks if the login details match
        if (isAuthenticated) { // if it is then return the id and account role.
            String role = accountService.retrieveRole(account.getUsername()); // get role from the username
            ObjectId id = accountService.retrieveId(account.getUsername()); // get the id from username
            return ResponseEntity.ok().body("{\"username\": \"" + account.getUsername() + "\", \"role\": \"" + role + "\", \"id\": \"" + id.toHexString() + "\"}"); // returns account details and their id for localstorage to store
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"UNAUTHORIZED\"}"); // let front end know that they are unauthorised
        }
    }

    @PutMapping("update/{id}") // put request to update account details based off of objectId
    public ResponseEntity<Account> updateAccount(@PathVariable("id") ObjectId id, // the id component
                                                 @RequestParam("currentPassword") String currentPassword, // ?currentPassword= (user has to input in their password) to verify that they want to update details
                                                 @RequestBody Account updatedAccount) { // the account to be updated

        Account updated = accountService.updateAccount(id, currentPassword, updatedAccount); // update account details based off of the id to update, their password, and the account
        if (updated != null) { // if an account exists then it updates successfully
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Or return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/logout") // post request to let database/backend know that the user has logged out
    public ResponseEntity<String> logoutAccount() {
        return ResponseEntity.ok("logout successful"); // just a reminder to database (abstract representation because project isn't focused on security, just functionality of flashcards)
    }

    @GetMapping("/{id}") // get account details based off of account details, returning the entire account object for a specific object
    public ResponseEntity<Optional<Account>> getAccount(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Account>>(accountService.findAccount(id), HttpStatus.OK);
    }

    @GetMapping // get ALL account objects.
    public ResponseEntity<List<Account>> getAllAccounts() { // retrieve all accounts from the database by calling the method allAccounts() which returns the data in an account list
        return new ResponseEntity<List<Account>>(accountService.allAccounts(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable ObjectId id, @RequestParam("currentPassword") String currentPassword) { // delete account with the current password as a parameter
        boolean deleted = accountService.deleteAccount(id, currentPassword); // deleteAccount method in service class
        if (deleted) { // if the account is deleted then
            return ResponseEntity.ok("Account deleted"); // response saying that the account has been deleted
        } else {
            return ResponseEntity.notFound().build(); // if not then return account not found
        }
    }



}
