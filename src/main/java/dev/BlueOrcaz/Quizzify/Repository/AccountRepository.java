package dev.BlueOrcaz.Quizzify.Repository;

import dev.BlueOrcaz.Quizzify.Model.Account;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends MongoRepository<Account, ObjectId> {
    Account findByUsername(String username);
}
