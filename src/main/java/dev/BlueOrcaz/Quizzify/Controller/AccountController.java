package dev.BlueOrcaz.Quizzify.Controller;

import dev.BlueOrcaz.Quizzify.Model.Account;
import dev.BlueOrcaz.Quizzify.Model.FlashcardSet;
import dev.BlueOrcaz.Quizzify.Service.AccountService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/v1/accounts")
@CrossOrigin(origins = "*")

public class AccountController {
    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account.getUsername(),
                account.getPassword(),
                account.getEmail(),
                account.getDateOfBirth(),
                account.getEducationalRole(),
                account.getRole(),
                account.getCreatedFlashcardSetsArrayList(),
                account.getCreatedFoldersArrayList());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }


    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> loginAccount(@RequestBody Account account) {
        boolean isAuthenticated = accountService.login(account.getUsername(), account.getPassword());
        if (isAuthenticated) {
            String role = accountService.retrieveRole(account.getUsername());
            ObjectId id = accountService.retrieveId(account.getUsername());
            return ResponseEntity.ok().body("{\"username\": \"" + account.getUsername() + "\", \"role\": \"" + role + "\", \"id\": \"" + id.toHexString() + "\"}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"UNAUTHORIZED\"}");
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") ObjectId id,
                                                 @RequestParam("currentPassword") String currentPassword,
                                                 @RequestBody Account updatedAccount) {

        Account updated = accountService.updateAccount(id, currentPassword, updatedAccount);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Or return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutAccount() {
        return ResponseEntity.ok("logout successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Account>> getAccount(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Account>>(accountService.findAccount(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<List<Account>>(accountService.allAccounts(), HttpStatus.OK);
    }



}
