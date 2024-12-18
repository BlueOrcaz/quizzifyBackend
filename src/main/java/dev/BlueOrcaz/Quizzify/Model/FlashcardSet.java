package dev.BlueOrcaz.Quizzify.Model;

import dev.BlueOrcaz.Quizzify.Model.Flashcards.Flashcard;
import dev.BlueOrcaz.Quizzify.Model.Flashcards.MCQFlashcard;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "flashcardSets")
@Data

public class FlashcardSet {
    @Id // generated when added to the database
    private String id;
    private String authorId; // the user's objectid
    private String authorUsername;

    // flashcard details
    private String setType;
    private boolean isPublic;
    private String name;
    private String description;
    private String creationDate;

    // stored flashcards based off of an arraylist
    private ArrayList<Flashcard> flashcards;
    private ArrayList<MCQFlashcard> mcqFlashcards;


    // constructor
    public FlashcardSet(String authorId, String authorUsername, String setType, boolean isPublic, String name, String description, String creationDate, ArrayList<Flashcard> flashcards, ArrayList<MCQFlashcard> mcqFlashcards) {
        this.authorId = authorId;
        this.authorUsername = authorUsername;
        this.setType = setType;
        this.isPublic = isPublic;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.flashcards = flashcards;
        this.mcqFlashcards = mcqFlashcards;
    }

    //getter setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getSetType() {
        return setType;
    }

    public void setSetType(String setType) {
        this.setType = setType;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public ArrayList<Flashcard> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(ArrayList<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }

    public ArrayList<MCQFlashcard> getMcqFlashcards() {
        return mcqFlashcards;
    }

    public void setMcqFlashcards(ArrayList<MCQFlashcard> mcqFlashcards) {
        this.mcqFlashcards = mcqFlashcards;
    }

}
