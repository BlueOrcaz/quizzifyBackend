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

@RestController // tell spring boot that it is a controller class
@RequestMapping("/api/v1/folders") // start of api
@CrossOrigin(origins = "*")

public class FolderController {
    @Autowired  // auto wire dependencies
    private final FolderService folderService; // folderservice class is added here so that its methods can be used

    public FolderController(FolderService folderService) { // constructor
        this.folderService = folderService;
    }

    @GetMapping("/{id}") // get a specific folder id by inputting in the id as a path. the findFolder() method is then called to get the specific folder id
    public ResponseEntity<Optional<Folder>> getFolder(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<Folder>>(folderService.findFolder(id), HttpStatus.OK);
    }

    @PostMapping("/createFolder") // POST request to create a folder with the parameters
    public ResponseEntity<String> createFolder(@RequestBody Folder folder) {
        Folder createdFolder = folderService.createFolder(folder.getId(),
                folder.getAuthorId(),
                folder.getFolderName(),
                folder.getCreationDate(),
                folder.getStoredFlashcardSets());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFolder.getId().toString()); // return the id
    }

    @PutMapping("/update/{id}") // PUT request to update a specific flashcard set with the new data
    public ResponseEntity<Folder> updateFolder(@PathVariable("id") ObjectId id,
                                               @RequestParam("authorId") String authorId, // params required
                                               @RequestBody Folder updatedFolder) {
        Folder updated = folderService.updateFolder(id, authorId, updatedFolder); // call the method from backend
        if (updated != null) {
            return  ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @DeleteMapping("/deleteFolder/{id}") // DELETE request to delete a specific folder from backend
    public ResponseEntity<String> deleteFolder(@PathVariable ObjectId id, @RequestParam("authorId") ObjectId accountId) {
        boolean deleted = folderService.deleteFolder(id, accountId); // only deletes if the id matches
        if (deleted) { // if they do
            return ResponseEntity.ok("Folder Deleted"); // successfully deleted
        } else {
            return ResponseEntity.notFound().build(); // failed to delete
        }
    }
}
