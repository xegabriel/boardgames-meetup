package app.boardgames.bgdashboard.services;

import app.boardgames.bgdashboard.dao.EventRepository;
import app.boardgames.bgdashboard.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }
}
