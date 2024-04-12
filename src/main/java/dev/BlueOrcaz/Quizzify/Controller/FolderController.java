package dev.BlueOrcaz.Quizzify.Controller;
import dev.BlueOrcaz.Quizzify.Model.FlashcardSet;
import dev.BlueOrcaz.Quizzify.Model.Folder;
import dev.BlueOrcaz.Quizzify.Service.FolderService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/folders")
@CrossOrigin(origins = "*")

public class FolderController {
    @Autowired
    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Folder>> getFolder(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<Folder>>(folderService.findFolder(id), HttpStatus.OK);
    }

    @PostMapping("/createFolder")
    public ResponseEntity<String> createFolder(@RequestBody Folder folder) {
        Folder createdFolder = folderService.createFolder(folder.getId(), folder.getAuthorId(), folder.getFolderName(), folder.getCreationDate(), folder.getStoredFlashcardSets());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFolder.getId().toString());
    }

    @PostMapping("/{folderId}/flashcardSets")
    public ResponseEntity<String> addFlashcardSetToFolder(@PathVariable("folderId") ObjectId folderId, @RequestBody FlashcardSet flashcardSet) {
        folderService.addFlashcardSetToFolder(folderId, flashcardSet);
        return ResponseEntity.status(HttpStatus.CREATED).body("Saved");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Folder> updateFolder(@PathVariable("id") ObjectId id,
                                               @RequestParam("authorId") String authorId,
                                               @RequestBody Folder updatedFolder) {
        Folder updated = folderService.updateFolder(id, authorId, updatedFolder);
        if (updated != null) {
            return  ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<String> deleteFolder(@PathVariable ObjectId id) {
        boolean deleted = folderService.deleteFolder(id);
        if (deleted) {
            return ResponseEntity.ok("Folder Deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
