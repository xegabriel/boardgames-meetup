package app.boardgames.bgcore.dao;

import app.boardgames.bgcore.domain.Event;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, ObjectId> {
    Event findByTitle(String title);
}
