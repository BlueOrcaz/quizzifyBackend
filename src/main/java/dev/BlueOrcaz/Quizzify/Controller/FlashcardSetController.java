package dev.BlueOrcaz.Quizzify.Controller;

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


    @GetMapping("/{id}")
    public ResponseEntity<Optional<FlashcardSet>> getFlashcardSet(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<FlashcardSet>>(flashcardSetService.findFlashcardSet(id), HttpStatus.OK);
    }

    @PostMapping("/createFlashcardSet")
    public ResponseEntity<String> createFlashcardSet(@RequestBody FlashcardSet flashcardSet) {
        FlashcardSet createdSet = flashcardSetService.createFlashcardSet(
                flashcardSet.getAuthorId(),
                flashcardSet.getSetType(),
                flashcardSet.isPublic(),
                flashcardSet.getName(),
                flashcardSet.getDescription(),
                flashcardSet.getCreationDate(),
                flashcardSet.getFlashcards(),
                flashcardSet.getMcqFlashcards());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSet.getId().toString());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FlashcardSet> updateFlashcardSet(@PathVariable("id") ObjectId id,
                                                           @RequestParam("authorId") ObjectId authorId,
                                                           @RequestBody FlashcardSet updatedFlashcardSet) {
        FlashcardSet updated = flashcardSetService.updateFlashcardSet(id, authorId, updatedFlashcardSet);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Or return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
