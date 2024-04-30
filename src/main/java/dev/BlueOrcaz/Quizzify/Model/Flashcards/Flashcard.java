package dev.BlueOrcaz.Quizzify.Model.Flashcards;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "flashcards") // where the flashcards will be stored in the database
@Data // tell spring boot that this is a database object/entry

public class Flashcard {
    // flashcard object consisting of an id, front, and back
    private int id; // id in the flashcard set; this is one flashcard amongst multiple flashcard sets
    private String front; // value
    private String back; // value

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }
}
