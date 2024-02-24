package dev.BlueOrcaz.Quizzify.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Flashcards")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Flashcards {
    @Id
    private ObjectId id;
    private String setId;
    private String name;
    private String description;
}
