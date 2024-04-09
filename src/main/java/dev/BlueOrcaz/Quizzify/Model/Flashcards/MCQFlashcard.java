package dev.BlueOrcaz.Quizzify.Model.Flashcards;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "flashcards")
@Data
public class MCQFlashcard {
    // mcq flashcard consisting of an id, question, as well as an option arraylist of all possible options
    private String id;
    private String question;
    private ArrayList<MCQOption> allOptions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<MCQOption> getAllOptions() {
        return allOptions;
    }

    public void setAllOptions(ArrayList<MCQOption> allOptions) {
        this.allOptions = allOptions;
    }
}
