package dev.BlueOrcaz.Quizzify.Service;

import dev.BlueOrcaz.Quizzify.Model.FlashcardSet;
import dev.BlueOrcaz.Quizzify.Model.Folder;
import dev.BlueOrcaz.Quizzify.Repository.FolderRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class FolderService {
    @Autowired
    private final FolderRepository folderRepository;
    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public Folder createFolder(ObjectId id, ObjectId authorId, String folderName, String description, String author, String creationDate, List<ObjectId> storedFlashcardSets, boolean isPublic) {
        return folderRepository.insert(new Folder(id, authorId, folderName, description, author, creationDate, storedFlashcardSets, isPublic));
    }

    public List<Folder> allFolders() { return folderRepository.findAll(); }
    public Optional<Folder> findFolder(ObjectId id) { return folderRepository.findById(id); }
}
