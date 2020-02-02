package app.boardgames.bgdashboard.services;

import app.boardgames.bgdashboard.dao.EventRepository;
import app.boardgames.bgdashboard.dao.UserRepository;
import app.boardgames.bgdashboard.domain.*;
import app.boardgames.bgdashboard.exceptions.EventNotFoundException;
import app.boardgames.bgdashboard.exceptions.IllegalOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    public Event saveEvent(String email, Event event) {
        event.startRegistration();
        event.setCreatedByEmail(email);
        return eventRepository.save(event);
    }

    public Event updateEvent(String email, Event event) {
        checkIfUserIsAllowed(event, email);
        return eventRepository.save(event);
    }

    public Event stopEventRegistration(String email, String eventTitle, String finalGame) {
        Event event = eventRepository.findByTitle(eventTitle);
        if (event == null) {
            throw new EventNotFoundException("Could not stop the registration, as the event does not exists!");

        }/* else if(!event.isEventStillAvailableForRegistration()){ // TODO: Decomment
            throw new IllegalOperationException("The event is already closed!");
        } */else if(!event.getAvailableGames().stream().map(AvailableGame::getGameName).collect(Collectors.toSet()).contains(finalGame)){
            throw new IllegalOperationException("The game is not available for this event!");
        }
        checkIfUserIsAllowed(event, email);
        event.stopRegistration();
        event.setFinalGame(finalGame);
        eventRepository.save(event);

        return event;
    }

    private void checkIfUserIsAllowed(Event event, String email) {
        if(event.getCreatedByEmail() != null && !event.getCreatedByEmail().equalsIgnoreCase(email)) {
            throw new IllegalOperationException("You are not allowed to edit this event!");
        }
    }

}
