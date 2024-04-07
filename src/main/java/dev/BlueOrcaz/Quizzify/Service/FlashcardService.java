package dev.BlueOrcaz.Quizzify.Service;

import dev.BlueOrcaz.Quizzify.Model.Flashcards.Flashcard;
import dev.BlueOrcaz.Quizzify.Repository.FlashcardRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class FlashcardService {
    @Autowired
    private final FlashcardRepository flashcardRepository;

    public FlashcardService(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }


}
