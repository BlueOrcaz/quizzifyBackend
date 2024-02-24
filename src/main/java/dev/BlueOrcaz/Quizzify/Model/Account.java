package dev.BlueOrcaz.Quizzify.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;

@Document(collection = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Account {
    @Id // unique identifier
    private ObjectId id;
    private String accountId;
    private String username;
    private String password;
    private String email;
    private String dateOfBirth;
    private String educationalRole;
    @DocumentReference
    private ArrayList<Flashcards> flashcardsArrayList;
    private ArrayList<Folder> folderArrayList;


    public Account(String accountId,
                   String username,
                   String password,
                   String email,
                   String dateOfBirth,
                   String educationalRole)
    {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.educationalRole = educationalRole;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEducationalRole() {
        return educationalRole;
    }

    public void setEducationalRole(String educationalRole) {
        this.educationalRole = educationalRole;
    }

    public ArrayList<Flashcards> getFlashcardsArrayList() {
        return flashcardsArrayList;
    }

    public void setFlashcardsArrayList(ArrayList<Flashcards> flashcardsArrayList) {
        this.flashcardsArrayList = flashcardsArrayList;
    }

    public ArrayList<Folder> getFolderArrayList() {
        return folderArrayList;
    }

    public void setFolderArrayList(ArrayList<Folder> folderArrayList) {
        this.folderArrayList = folderArrayList;
    }
}
