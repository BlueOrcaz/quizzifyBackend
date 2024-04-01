package dev.BlueOrcaz.Quizzify.Controller;
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
@CrossOrigin(origins = "http://localhost:3000")

public class FolderController {
    @Autowired
    private final FolderService folderService;
    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping
    public ResponseEntity<Folder> createFolder(@RequestBody Folder folder) {
        Folder createdFolder = folderService.createFolder(folder.getId(), folder.getAuthorId(), folder.getFolderName(), folder.getDescription(), folder.getAuthor(), folder.getCreationDate(), folder.getStoredFlashcardSets(), folder.isPublic());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFolder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Folder>> getFolder(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Folder>>(folderService.findFolder(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Folder>> getAllFolders() {
        return new ResponseEntity<List<Folder>>(folderService.allFolders(), HttpStatus.OK);
    }


}
