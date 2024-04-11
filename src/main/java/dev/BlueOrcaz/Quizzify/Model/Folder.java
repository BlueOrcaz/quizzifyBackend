package dev.BlueOrcaz.Quizzify.Model;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "folders")
@Data

public class Folder {
    @Id
    private ObjectId id;
    private ObjectId authorId;
    private String folderName;
    private String creationDate;
    private ArrayList<String> storedFlashcardSets;

    public Folder(ObjectId id, ObjectId authorId, String folderName, String creationDate, ArrayList<String> storedFlashcardSets) {
        this.id = id;
        this.authorId = authorId;
        this.folderName = folderName;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public ArrayList<String> getStoredFlashcardSets() {
        return storedFlashcardSets;
    }

    public void setStoredFlashcardSets(ArrayList<String> storedFlashcardSets) {
        this.storedFlashcardSets = storedFlashcardSets;
    }
}
