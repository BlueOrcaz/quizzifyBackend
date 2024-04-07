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
    private final FlashcardSetRepository flashcardSetRepository;
    public FlashcardSetService(FlashcardSetRepository flashcardSetRepository) {
        this.flashcardSetRepository = flashcardSetRepository;
    }
    public List<FlashcardSet> allFlashcardSets() { return flashcardSetRepository.findAll(); }
    public Optional<FlashcardSet> findFlashcardSet(ObjectId id) { return flashcardSetRepository.findById(id); }

    public FlashcardSet createFlashcardSet(ObjectId authorId,
                                           String setType,
                                           boolean isPublic,
                                           String setName,
                                           String setDescription,
                                           ArrayList<Flashcard> flashcards,
                                           ArrayList<MCQFlashcard> mcqFlashcards
                                           ) {
        return flashcardSetRepository.insert(new FlashcardSet(authorId, setType, isPublic, setName, setDescription, flashcards, mcqFlashcards));
    }
}
