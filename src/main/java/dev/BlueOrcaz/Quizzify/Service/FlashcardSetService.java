package dev.BlueOrcaz.Quizzify.Service;

import dev.BlueOrcaz.Quizzify.Model.FlashcardSet;
import dev.BlueOrcaz.Quizzify.Model.Flashcards.Flashcard;
import dev.BlueOrcaz.Quizzify.Model.Flashcards.MCQFlashcard;
import dev.BlueOrcaz.Quizzify.Repository.FlashcardSetRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlashcardSetService {
    @Autowired
    private final FlashcardSetRepository flashcardSetRepository; // flashcard set repository
    public FlashcardSetService(FlashcardSetRepository flashcardSetRepository) { // constructor
        this.flashcardSetRepository = flashcardSetRepository;
    }
    public List<FlashcardSet> allFlashcardSets() { return flashcardSetRepository.findAll(); } // list of all flashcard sets
    public Optional<FlashcardSet> findFlashcardSet(ObjectId id) { return flashcardSetRepository.findById(id); } // find a flashcard set object based off of a specific id


    // create a flashcard set based off of all details.
    public FlashcardSet createFlashcardSet(ObjectId authorId,
                                           String setType,
                                           boolean isPublic,
                                           String setName,
                                           String setDescription,
                                           String creationDate,
                                           ArrayList<Flashcard> flashcards,
                                           ArrayList<MCQFlashcard> mcqFlashcards
                                           ) {
        return flashcardSetRepository.insert(new FlashcardSet(
                authorId,
                setType,
                isPublic,
                setName,
                setDescription,
                creationDate,
                flashcards,
                mcqFlashcards
        ));
    }


    // updates flashcard set based off of the id, author id and flashcard set object
    public FlashcardSet updateFlashcardSet(ObjectId id, ObjectId authorId, FlashcardSet updatedFlashcardSet) {
        FlashcardSet existingSet = flashcardSetRepository.findById(id).orElse(null); // if it doesnt find anything, return null
        if(existingSet == null) {
            return null;
        }
        if(!authorId.equals(existingSet.getAuthorId()) ) {
            return null;
        }

        existingSet.setName(updatedFlashcardSet.getName());
        existingSet.setSetType(updatedFlashcardSet.getSetType());
        existingSet.setPublic(updatedFlashcardSet.isPublic());
        existingSet.setDescription(updatedFlashcardSet.getDescription());
        existingSet.setFlashcards(updatedFlashcardSet.getFlashcards());
        existingSet.setMcqFlashcards(updatedFlashcardSet.getMcqFlashcards());

        return flashcardSetRepository.save(existingSet); // overwrite the stored flashcard set with the new details.
    }

}
