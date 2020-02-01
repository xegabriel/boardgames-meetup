package app.boardgames.bgcore.controller;

import app.boardgames.bgcore.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/core")
public class EventController {
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/getAllEvents", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @RequestMapping(value = "/voteEvent/{eventTitle}/{gameName}", method = RequestMethod.PUT)
    public ResponseEntity<?> voteEvent(HttpServletRequest request, @PathVariable String eventTitle, @PathVariable String gameName) {
        return ResponseEntity.ok(eventService.voteEvent(request.getHeader("email"), eventTitle, gameName));
    }
}
