package dev.BlueOrcaz.Quizzify.Service;

import dev.BlueOrcaz.Quizzify.Model.FlashcardSet;
import dev.BlueOrcaz.Quizzify.Model.Folder;
import dev.BlueOrcaz.Quizzify.Repository.FolderRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class FolderService {
    // stores all the methods in the backend
    @Autowired
    private final FolderRepository folderRepository;

    @Autowired
    private final AccountService accountService;

    public FolderService(FolderRepository folderRepository, AccountService accountService) {
        this.folderRepository = folderRepository;
        this.accountService = accountService;
    }

    public Folder createFolder(ObjectId id, ObjectId authorId, String folderName, String creationDate, ArrayList<String> storedFlashcardSets) { // create folder based off of params
        Folder newFolder = folderRepository.insert(new Folder(id, authorId, folderName, creationDate, storedFlashcardSets));
        accountService.addFolderSetToAccount(authorId, newFolder);
        return newFolder;
    }

    public Folder updateFolder(ObjectId id, String authorId, Folder updatedFolder) { // update folder if the correct author id is displayed
        Folder existingFolder = folderRepository.findById(id).orElse(null);

        if(existingFolder == null) {
            return null;
        }

        if(!authorId.matches(updatedFolder.getAuthorId().toString())) {
            return null;
        }

        existingFolder.setFolderName(updatedFolder.getFolderName());
        existingFolder.setStoredFlashcardSets(updatedFolder.getStoredFlashcardSets());

        return folderRepository.save(existingFolder);
    }

    public boolean deleteFolder(ObjectId folderId) {
        Optional<Folder> folderOptional = folderRepository.findById(folderId);

        if (folderOptional.isPresent()) {
            Folder folder = folderOptional.get();
            folderRepository.delete(folder);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Folder> findFolder(ObjectId id) {
        return folderRepository.findById(id);
    }

    public void addFlashcardSetToFolder(ObjectId folderId, FlashcardSet flashcardSet) {
        Folder folder = folderRepository.findById(folderId).orElseThrow(() -> new RuntimeException("Folder Not Found"));
        folder.getStoredFlashcardSets().add((flashcardSet.getId().toString()));
        folderRepository.save(folder);
    }
}
