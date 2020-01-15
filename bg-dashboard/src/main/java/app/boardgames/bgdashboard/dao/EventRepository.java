package app.boardgames.bgdashboard.dao;

import app.boardgames.bgdashboard.domain.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, Long> {
}
