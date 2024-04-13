package dev.BlueOrcaz.Quizzify.Service;

import dev.BlueOrcaz.Quizzify.Model.Account;
import dev.BlueOrcaz.Quizzify.Model.FlashcardSet;
import dev.BlueOrcaz.Quizzify.Model.Flashcards.Flashcard;
import dev.BlueOrcaz.Quizzify.Model.Flashcards.MCQFlashcard;
import dev.BlueOrcaz.Quizzify.Repository.AccountRepository;
import dev.BlueOrcaz.Quizzify.Repository.FlashcardSetRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class FlashcardSetService {
    @Autowired
    private final FlashcardSetRepository flashcardSetRepository; // flashcard set repository
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    public FlashcardSetService(FlashcardSetRepository flashcardSetRepository, AccountRepository accountRepository, AccountService accountService) { // constructor
        this.flashcardSetRepository = flashcardSetRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }
    public List<FlashcardSet> allFlashcardSets() { return flashcardSetRepository.findAll(); } // list of all flashcard sets
    public Optional<FlashcardSet> findFlashcardSet(ObjectId id) { return flashcardSetRepository.findById(id); } // find a flashcard set object based off of a specific id

    public Optional<FlashcardSet> findAllFlashcardSetsBasedOffAuthorId(ObjectId authorId) {
        return flashcardSetRepository.findAllById(authorId);
    }

    public String retrieveFlashcardString(FlashcardSet flashcardSet) {
        return flashcardSet.getId().toString();
    }


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

        FlashcardSet newFlashcardSet = flashcardSetRepository.insert(new FlashcardSet(
                authorId,
                setType,
                isPublic,
                setName,
                setDescription,
                creationDate,
                flashcards,
                mcqFlashcards
        ));

        accountService.addFlashcardSetToAccount(authorId, newFlashcardSet);

        return newFlashcardSet;
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

    public boolean deleteFlashcardSet(ObjectId accountId, ObjectId flashcardId) {
        // Only allowed to delete if you are the owner
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        Optional<FlashcardSet> flashcardSetOptional = flashcardSetRepository.findById(flashcardId);

        if (accountOptional.isPresent() && flashcardSetOptional.isPresent()) {
            Account account = accountOptional.get();
            FlashcardSet flashcardSet = flashcardSetOptional.get();
            ArrayList<String> flashcardSets = account.getCreatedFlashcardSetsArrayList();

            // Check if the flashcard set ID exists in the account's list
            if (flashcardSets.contains(flashcardId.toString())) {
                // Remove the flashcard set ID from the list
                flashcardSets.remove(flashcardId.toString());
                // Update the account with the removed flashcard set ID
                account.setCreatedFlashcardSetsArrayList(flashcardSets);
                // Save the updated account
                accountRepository.save(account);
                // Delete the flashcard set from the repository
                flashcardSetRepository.delete(flashcardSet);
                return true;
            }
        }
        return false; // If account or flashcard set not found, or not owner, return false
    }




}
