package dev.BlueOrcaz.Quizzify.Model.Flashcards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "flashcards")
@Data

public class Flashcard {
    private int flashcardId;
    private String front;
    private String back;
}