package dev.BlueOrcaz.Quizzify.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.LinkedList;

@Document(collection = "flashcardSets")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FlashcardSet {
    @Id
    private ObjectId id;
    private String setName;
    private String setDescription;
    private boolean isPublic;
    private String creationDate;
    private ArrayList<ObjectId> flashcardIds;
    @DocumentReference
    private String author;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getSetDescription() {
        return setDescription;
    }

    public void setSetDescription(String setDescription) {
        this.setDescription = setDescription;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public ArrayList<ObjectId> getFlashcardIds() {
        return flashcardIds;
    }

    public void setFlashcardIds(ArrayList<ObjectId> flashcardIds) {
        this.flashcardIds = flashcardIds;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
