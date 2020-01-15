package app.boardgames.bgcore.dao;

import app.boardgames.bgcore.domain.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, Long> {
}
