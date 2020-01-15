package app.boardgames.bgcore.services;

import app.boardgames.bgcore.dao.EventRepository;
import app.boardgames.bgcore.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
