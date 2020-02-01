package app.boardgames.bgcore.dao;

import app.boardgames.bgcore.domain.CompactUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompactUserRepository extends MongoRepository<CompactUser, ObjectId> {
    CompactUser findByEmail(String email);
}
