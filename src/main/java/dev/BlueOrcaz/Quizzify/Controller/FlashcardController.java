package dev.BlueOrcaz.Quizzify.Controller;

import dev.BlueOrcaz.Quizzify.Model.Flashcards.Flashcard;
import dev.BlueOrcaz.Quizzify.Service.FlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/flashcards")
@CrossOrigin(origins = "http://localhost:3000")

public class FlashcardController {
    @Autowired
    private final FlashcardService flashcardService;
    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @PostMapping
    public ResponseEntity<Flashcard> createFlashcard(@RequestBody Flashcard flashcard){
        Flashcard createdFlashcard = flashcardService.createFlashcard(
                flashcard.getId(),
                flashcard.getQuestion(),
                flashcard.getQuestionType(),
                flashcard.getOptions(),
                flashcard.getAnswer());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFlashcard);
    }
}
