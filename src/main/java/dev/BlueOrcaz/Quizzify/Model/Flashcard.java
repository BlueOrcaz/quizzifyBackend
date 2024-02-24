package dev.BlueOrcaz.Quizzify.Model;

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
    @Id
    private ObjectId id;
    private String question;
    private String questionType;
    private List<String> options;
    private List<String> answer;

    public Flashcard(ObjectId id,
                     String question,
                     String questionType,
                     List<String> options,
                     List<String> answer) {
        this.id = id;
        this.question = question;
        this.questionType = questionType;
        this.options = options;
        this.answer = answer;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }
}
