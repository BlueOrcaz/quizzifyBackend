package dev.BlueOrcaz.Quizzify.Repository;

import dev.BlueOrcaz.Quizzify.Model.FlashcardSet;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface FlashcardSetRepository extends MongoRepository<FlashcardSet, ObjectId> {
    Optional<FlashcardSet> findAllById(ObjectId authorId); // allows for backend to search for details based off of an id
}
