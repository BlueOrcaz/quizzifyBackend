package dev.BlueOrcaz.Quizzify.Model.Flashcards;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "flashcards")
@Data
public class MCQFlashcard {
    private String id;
    private String question;
    private ArrayList<MCQOption> allOptions;
}
