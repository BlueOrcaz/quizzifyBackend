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
    private String author;
    private String creationDate;
    private List<ObjectId> storedFlashcardSets;
    private boolean isPublic;

    public Folder(ObjectId id, ObjectId authorId, String folderName, String description, String author, String creationDate, List<ObjectId> storedFlashcardSets, boolean isPublic) {
        this.id = id;
        this.authorId = authorId;
        this.folderName = folderName;
        this.description = description;
        this.author = author;
        this.creationDate = creationDate;
        this.storedFlashcardSets = storedFlashcardSets;
        this.isPublic = isPublic;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List<ObjectId> getStoredFlashcardSets() {
        return storedFlashcardSets;
    }

    public void setStoredFlashcardSets(List<ObjectId> storedFlashcardSets) {
        this.storedFlashcardSets = storedFlashcardSets;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
