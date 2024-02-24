package dev.BlueOrcaz.Quizzify.Repository;

import dev.BlueOrcaz.Quizzify.Model.Folder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FolderRepository extends MongoRepository<Folder, ObjectId> {
}
