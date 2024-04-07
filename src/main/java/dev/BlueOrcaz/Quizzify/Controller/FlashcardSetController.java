package dev.BlueOrcaz.Quizzify.Controller;

import dev.BlueOrcaz.Quizzify.Model.Account;
import dev.BlueOrcaz.Quizzify.Model.FlashcardSet;
import dev.BlueOrcaz.Quizzify.Model.Flashcards.Flashcard;
import dev.BlueOrcaz.Quizzify.Service.FlashcardSetService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/flashcardSets")
@CrossOrigin(origins = "http://localhost:3000")
public class FlashcardSetController {
    @Autowired
    private final FlashcardSetService flashcardSetService;
    public FlashcardSetController(FlashcardSetService flashcardSetService) {
        this.flashcardSetService = flashcardSetService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<FlashcardSet>> getFlashcardSet(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<FlashcardSet>>(flashcardSetService.findFlashcardSet(id), HttpStatus.OK);
    }

    @PostMapping("/createFlashcardSet")
    public ResponseEntity<FlashcardSet> createFlashcardSet(@RequestBody FlashcardSet flashcardSet) {
        FlashcardSet createdSet = flashcardSetService.createFlashcardSet(flashcardSet.getAuthorId(),
                flashcardSet.getSetType(),
                flashcardSet.isPublic(),
                flashcardSet.getName(),
                flashcardSet.getDescription(),
                flashcardSet.getFlashcards(),
                flashcardSet.getMcqFlashcards());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSet);
    }

}
