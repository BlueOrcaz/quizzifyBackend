package dev.BlueOrcaz.Quizzify.Service;

import dev.BlueOrcaz.Quizzify.Model.FlashcardSet;
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
    private final FlashcardSetRepository flashcardSetRepository;
    public FlashcardSetService(FlashcardSetRepository flashcardSetRepository) {
        this.flashcardSetRepository = flashcardSetRepository;
    }

    public FlashcardSet createFlashcardSet(ObjectId id,
                                           ObjectId authorId,
                                           String setName,
                                           String setDescription,
                                           boolean isPublic,
                                           String creationDate,
                                           ArrayList<ObjectId> flashcardIds,
                                           String author) {
       return flashcardSetRepository.insert(new FlashcardSet(
               id,
               authorId,
               setName,
               setDescription,
               isPublic,
               creationDate,
               flashcardIds,
               author));
    }

    public List<FlashcardSet> allFlashcardSets() { return flashcardSetRepository.findAll(); }
    public Optional<FlashcardSet> findFlashcardSet(ObjectId id) { return flashcardSetRepository.findById(id); }


}
