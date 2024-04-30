package dev.BlueOrcaz.Quizzify.Service;

import dev.BlueOrcaz.Quizzify.Model.Account;
import dev.BlueOrcaz.Quizzify.Model.Folder;
import dev.BlueOrcaz.Quizzify.Repository.AccountRepository;
import dev.BlueOrcaz.Quizzify.Repository.FolderRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service

public class FolderService {
    // stores all the methods in the backend
    @Autowired // autowire dependencies
    private final FolderRepository folderRepository; // repos
    private final AccountRepository accountRepository;

    @Autowired
    private final AccountService accountService; // account service

    public FolderService(FolderRepository folderRepository, AccountService accountService, AccountRepository accountRepository) { // constructor
        this.folderRepository = folderRepository;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    public Folder createFolder(ObjectId id, ObjectId authorId, String folderName, String creationDate, ArrayList<String> storedFlashcardSets) { // create folder based off of params
        Folder newFolder = folderRepository.insert(new Folder(id, authorId, folderName, creationDate, storedFlashcardSets)); // create new folder obj
        accountService.addFolderSetToAccount(authorId, newFolder); // add it to account
        return newFolder;
    }

    public Folder updateFolder(ObjectId id, String authorId, Folder updatedFolder) { // update folder if the correct author id is displayed
        Folder existingFolder = folderRepository.findById(id).orElse(null);

        if(existingFolder == null) {
            return null; // if no folder is found
        }

        if(!authorId.matches(updatedFolder.getAuthorId().toString())) { // if the author id doesnt match to the updated folder then retunr null
            return null;
        }

        existingFolder.setFolderName(updatedFolder.getFolderName()); // update
        existingFolder.setStoredFlashcardSets(updatedFolder.getStoredFlashcardSets());

        return folderRepository.save(existingFolder); // update
    }

    public boolean deleteFolder(ObjectId folderId, ObjectId accountId) { // delete folder based off of folder id and account id
        Optional<Folder> folderOptional = folderRepository.findById(folderId);
        Optional<Account> accountOptional = accountRepository.findById(accountId);

        if (accountOptional.isPresent() && folderOptional.isPresent()) {
            Account account = accountOptional.get(); // get details
            Folder folder = folderOptional.get();
            ArrayList<String> folders = account.getCreatedFoldersArrayList(); // get arraylist of folders

            if (folders.contains(folderId.toString())) { // if the folder id matches the stuff in arraylist
                folders.remove(folderId.toString()); // remove
                account.setCreatedFoldersArrayList(folders); // update
                accountRepository.save(account); // update
                folderRepository.delete(folder); // delete entry
                return true;
            }
            return false;
        }
        return false;
    }

    public Optional<Folder> findFolder(ObjectId id) {
        return folderRepository.findById(id);
    }
}
