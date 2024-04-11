package dev.BlueOrcaz.Quizzify.Model;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "Folders")
@Data

public class Folder {
    @Id
    private ObjectId id;
    private ObjectId authorId;
    private String folderName;
    private String description;
    private String creationDate;
    private List<FlashcardSet> storedFlashcardSets;

    public Folder(ObjectId id, ObjectId authorId, String folderName, String description, String creationDate, List<FlashcardSet> storedFlashcardSets) {
        this.id = id;
        this.authorId = authorId;
        this.folderName = folderName;
        this.description = description;
        this.creationDate = creationDate;
        this.storedFlashcardSets = storedFlashcardSets;
    }

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

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
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

    public List<FlashcardSet> getStoredFlashcardSets() {
        return storedFlashcardSets;
    }

    public void setStoredFlashcardSets(List<FlashcardSet> storedFlashcardSets) {
        this.storedFlashcardSets = storedFlashcardSets;
    }
}
