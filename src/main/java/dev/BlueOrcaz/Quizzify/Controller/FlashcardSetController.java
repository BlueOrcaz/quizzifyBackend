package dev.BlueOrcaz.Quizzify.Controller;

import dev.BlueOrcaz.Quizzify.Model.Account;
import dev.BlueOrcaz.Quizzify.Model.FlashcardSet;
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

    @PostMapping
    public ResponseEntity<FlashcardSet> createFlashcardSet(@RequestBody FlashcardSet flashcardSet) {
        FlashcardSet createdFlashcardSet = flashcardSetService.createFlashcardSet(flashcardSet.getId(), flashcardSet.getAuthorId(), flashcardSet.getSetName(), flashcardSet.getSetDescription(), flashcardSet.isPublic(), flashcardSet.getCreationDate(), flashcardSet.getFlashcardIds(), flashcardSet.getAuthor());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFlashcardSet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<FlashcardSet>> getFlashcardSet(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<FlashcardSet>>(flashcardSetService.findFlashcardSet(id), HttpStatus.OK);
    }

}
