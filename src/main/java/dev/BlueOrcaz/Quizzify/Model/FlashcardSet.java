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
    @Id
    private ObjectId id;
    private ObjectId authorId;
    private String setType;
    private boolean isPublic;
    private String name;
    private String description;
    private ArrayList<Flashcard> flashcards;
    private ArrayList<MCQFlashcard> mcaFlashcards;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getAuthorId() {
        return authorId;
    }

    public void setAuthorId(ObjectId authorId) {
        this.authorId = authorId;
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

    public ArrayList<Flashcard> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(ArrayList<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }

    public ArrayList<MCQFlashcard> getMcaFlashcards() {
        return mcaFlashcards;
    }

    public void setMcaFlashcards(ArrayList<MCQFlashcard> mcaFlashcards) {
        this.mcaFlashcards = mcaFlashcards;
    }
}
