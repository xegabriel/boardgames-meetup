package app.boardgames.bgcore.services;

import app.boardgames.bgcore.dao.EventRepository;
import app.boardgames.bgcore.dao.CompactUserRepository;
import app.boardgames.bgcore.dao.UserRepository;
import app.boardgames.bgcore.domain.*;
import app.boardgames.bgcore.exceptions.EventIsDisabledException;
import app.boardgames.bgcore.exceptions.EventNotFoundException;
import app.boardgames.bgcore.exceptions.GameNotFoundException;
import app.boardgames.bgcore.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CompactUserRepository compactUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event voteEvent(String email, String eventTitle, String gameName) {
        CompactUser user = compactUserRepository.findByEmail(email);
        Event event = eventRepository.findByTitle(eventTitle);
        if(user == null) {
            throw new UserNotFoundException("The user with email " + email + " does not exist!");
        } else if (event == null) {
            throw new EventNotFoundException("The event " + eventTitle + " could not be found!");
        } else if(!event.getAvailableGames().stream().map(AvailableGame::getGameName).collect(Collectors.toSet()).contains(gameName)) {
            throw new GameNotFoundException("Game " + gameName + " not found!");
        } else if (!event.isEventStillAvailableForRegistration()) {
            throw new EventIsDisabledException("The event is not available for voting !");
        }

        Optional<AvailableGame> availableGameOptional = event.getAvailableGames().stream().filter(ag -> ag.getGameName().equalsIgnoreCase(gameName)).findFirst();
        if(availableGameOptional.isPresent()) {
            AvailableGame availableGameVotes =availableGameOptional.get();
            event.getAvailableGames().remove(availableGameVotes);
            availableGameVotes.pushOrRemoveVote(user);
            event.getAvailableGames().add(availableGameVotes);
        }

        return eventRepository.save(event);
    }

    public Event suggestGame(String email, String eventTitle, String gameName) {
        CompactUser user = compactUserRepository.findByEmail(email);
        Event event = eventRepository.findByTitle(eventTitle);
        if(user == null) {
            throw new UserNotFoundException("The user with email " + email + " does not exist!");
        } else if (event == null) {
            throw new EventNotFoundException("The event " + eventTitle + " could not be found!");
        } else if (!event.isEventStillAvailableForRegistration()) {
            throw new EventIsDisabledException("The event is not available for proposing games !");
        }

        event.pushSuggestedGame(new ProposedGame(gameName, user));
        return eventRepository.save(event);
    }

    public Event becomeInterested(String email, String eventTitle) {
        CompactUser user = compactUserRepository.findByEmail(email);
        Event event = eventRepository.findByTitle(eventTitle);
        if(user == null) {
            throw new UserNotFoundException("The user with email " + email + " does not exist!");
        } else if (event == null) {
            throw new EventNotFoundException("The event " + eventTitle + " could not be found!");
        } else if (!event.isEventStillAvailableForRegistration()) {
            throw new EventIsDisabledException("The event is not available for attending !");
        }

        event.pushOrRemoveInterestedPlayer(new InterestedUser(user));
        return eventRepository.save(event);
    }

    public Event confirmAttendance(String email, String eventTitle) {
        User user = userRepository.findByEmail(email);
        Event event = eventRepository.findByTitle(eventTitle);
        if(user == null) {
            throw new UserNotFoundException("The user with email " + email + " does not exist!");
        } else if (event == null) {
            throw new EventNotFoundException("The event " + eventTitle + " could not be found!");
        } else if (event.isEventStillAvailableForRegistration()) {
            throw new EventIsDisabledException("You can't confirm your attendance yet!");
        }

        event.confirmAttendance(user);
        userService.incrementNumberOfAttendances(user);
        return eventRepository.save(event);
    }
}
