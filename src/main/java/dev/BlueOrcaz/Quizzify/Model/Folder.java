package dev.BlueOrcaz.Quizzify.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;

@Document(collection = "Folders")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Folder {
    @Id
    private ObjectId id;
    private String setId;
    private String description;
    @DocumentReference
    private ArrayList<Flashcards> flashcardsArrayList;
}
