package app.boardgames.bgdashboard.services;

import app.boardgames.bgdashboard.dao.EventRepository;
import app.boardgames.bgdashboard.domain.Event;
import app.boardgames.bgdashboard.exceptions.EventNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event saveEvent(Event event) {
        event.startRegistration();
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event stopEventRegistration(String eventTitle) {
        Event event = eventRepository.findByTitle(eventTitle);
        if(event != null) {
            event.stopRegistration();
            eventRepository.save(event);
        } else {
            throw new EventNotFoundException("Could not stop the registration, as the event does not exists!");
        }
        //TODO: Implement players notifications
        return event;
    }
}
