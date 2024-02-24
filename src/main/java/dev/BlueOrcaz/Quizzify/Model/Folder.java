package dev.BlueOrcaz.Quizzify.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "Folders")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Folder {
    @Id
    private ObjectId id;

    private String folderName;
    private String description;
    private String author;
    private String creationDate;
    private List<ObjectId> storedFlashcardSets;
    private boolean isPublic;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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
