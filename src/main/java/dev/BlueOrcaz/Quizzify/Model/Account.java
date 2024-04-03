package dev.BlueOrcaz.Quizzify.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "accounts")
@Data

public class Account {
    @Id // unique identifier
    private ObjectId id;
    private String username;
    private String password;
    private String email;
    private String dateOfBirth;
    private String educationalRole;
    private String role;
    private ArrayList<ObjectId> createdFlashcardSetsArrayList;
    private ArrayList<ObjectId> createdFoldersArrayList;


    public Account(String username,
                   String password,
                   String email,
                   String dateOfBirth,
                   String educationalRole,
                   String role,
                   ArrayList<ObjectId> createdFlashcardSetsArrayList,
                   ArrayList<ObjectId> createdFoldersArrayList) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.educationalRole = educationalRole;
        this.role = role;
        this.createdFlashcardSetsArrayList = createdFlashcardSetsArrayList;
        this.createdFoldersArrayList = createdFoldersArrayList;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<ObjectId> getCreatedFlashcardSetsArrayList() {
        return createdFlashcardSetsArrayList;
    }

    public void setCreatedFlashcardSetsArrayList(ArrayList<ObjectId> createdFlashcardSetsArrayList) {
        this.createdFlashcardSetsArrayList = createdFlashcardSetsArrayList;
    }

    public ArrayList<ObjectId> getCreatedFoldersArrayList() {
        return createdFoldersArrayList;
    }

    public void setCreatedFoldersArrayList(ArrayList<ObjectId> createdFoldersArrayList) {
        this.createdFoldersArrayList = createdFoldersArrayList;
    }
}
