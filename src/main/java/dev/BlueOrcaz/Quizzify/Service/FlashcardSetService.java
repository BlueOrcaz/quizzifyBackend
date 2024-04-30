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


    public String retrieveFlashcardString(FlashcardSet flashcardSet) {
        return flashcardSet.getId().toString();
    }


    // create a flashcard set based off of all details.
    public FlashcardSet createFlashcardSet(String authorId, // params
                                           String authorUsername,
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
                authorUsername,
                setType,
                isPublic,
                setName,
                setDescription,
                creationDate,
                flashcards,
                mcqFlashcards
        ));

        ObjectId oAuthorId = new ObjectId(authorId);

        accountService.addFlashcardSetToAccount(oAuthorId, newFlashcardSet);

        return newFlashcardSet;
    }


    // updates flashcard set based off of the id, author id and flashcard set object
    public FlashcardSet updateFlashcardSet(ObjectId id, String authorId, FlashcardSet updatedFlashcardSet) {
        FlashcardSet existingSet = flashcardSetRepository.findById(id).orElse(null); // if it doesnt find anything, return null
        if(existingSet == null) {
            return null;
        }
        if(!authorId.equals(existingSet.getAuthorId()) ) {
            return null;
        }

        existingSet.setName(updatedFlashcardSet.getName());
        existingSet.setPublic(updatedFlashcardSet.isPublic());
        existingSet.setSetType(updatedFlashcardSet.getSetType());
        existingSet.setDescription(updatedFlashcardSet.getDescription());
        existingSet.setFlashcards(updatedFlashcardSet.getFlashcards());
        existingSet.setMcqFlashcards(updatedFlashcardSet.getMcqFlashcards());

        return flashcardSetRepository.save(existingSet); // overwrite the stored flashcard set with the new details.
    }

    public boolean deleteFlashcardSet(ObjectId accountId, ObjectId flashcardId) {
        // Only allowed to delete if you are the owner
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        Optional<FlashcardSet> flashcardSetOptional = flashcardSetRepository.findById(flashcardId);

        if (accountOptional.isPresent() && flashcardSetOptional.isPresent()) { // if an account and flashcard is found
            Account account = accountOptional.get(); // get account
            FlashcardSet flashcardSet = flashcardSetOptional.get();
            ArrayList<String> flashcardSets = account.getCreatedFlashcardSetsArrayList();

            // check if the flashcard set ID exists in the account's list
            for(int i = 0; i < flashcardSets.size(); i++) {
                if(flashcardSets.get(i).equals(flashcardId.toString())) {
                    flashcardSets.remove(i); // remove specific index
                    account.setCreatedFlashcardSetsArrayList(flashcardSets); // update arraylist
                    accountRepository.save(account); // update database for accounts
                    flashcardSetRepository.delete(flashcardSet); // remove entry from database entry
                    return true;
                }
            }
        }
        return false;
    }
    




}
