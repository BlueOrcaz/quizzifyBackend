package dev.BlueOrcaz.Quizzify.Controller;

import dev.BlueOrcaz.Quizzify.Model.Account;
import dev.BlueOrcaz.Quizzify.Model.FlashcardSet;
import dev.BlueOrcaz.Quizzify.Service.FlashcardSetService;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/flashcardSets")
@CrossOrigin(origins = "*")
public class FlashcardSetController {
    @Autowired
    private final FlashcardSetService flashcardSetService; // flashcard set service stores all necessary methods
    public FlashcardSetController(FlashcardSetService flashcardSetService) { // constructor
        this.flashcardSetService = flashcardSetService;
    }


    @GetMapping("/{id}") // retrieve a flashcard set based off of an ObjectId
    public ResponseEntity<Optional<FlashcardSet>> getFlashcardSet(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<FlashcardSet>>(flashcardSetService.findFlashcardSet(id), HttpStatus.OK);
    }

    @GetMapping // get ALL account objects.
    public ResponseEntity<List<FlashcardSet>> getAllFlashcardSets() {
        return new ResponseEntity<List<FlashcardSet>>(flashcardSetService.allFlashcardSets(), HttpStatus.OK);
    }


    @PostMapping("/createFlashcardSet") // sends post request to the server to create a flashcard set with all details
    public ResponseEntity<String> createFlashcardSet(@RequestBody FlashcardSet flashcardSet) {
        FlashcardSet createdSet = flashcardSetService.createFlashcardSet(
                flashcardSet.getAuthorId(),
                flashcardSet.getAuthorUsername(),
                flashcardSet.getSetType(),
                flashcardSet.isPublic(),
                flashcardSet.getName(),
                flashcardSet.getDescription(),
                flashcardSet.getCreationDate(),
                flashcardSet.getFlashcards(),
                flashcardSet.getMcqFlashcards());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSet.getId().toString()); // if successful then return the id for the user to use.
    }



    @PutMapping("/update/{id}") // put request to update flashcards. Only allowed to update flashcards you made.
    public ResponseEntity<FlashcardSet> updateFlashcardSet(@PathVariable("id") ObjectId id,
                                                           @RequestParam("authorId") String authorId,
                                                           @RequestBody FlashcardSet updatedFlashcardSet) {
        FlashcardSet updated = flashcardSetService.updateFlashcardSet(id, authorId, updatedFlashcardSet);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Or return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deleteFlashcardSet/{id}")
    public ResponseEntity<String> deleteFlashcardSet(@PathVariable ObjectId id, @RequestParam("authorId") ObjectId accountId) {
        boolean deleted = flashcardSetService.deleteFlashcardSet(accountId, id);
        if (deleted) {
            return ResponseEntity.ok("Flashcard Set Deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
