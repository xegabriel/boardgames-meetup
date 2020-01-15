package app.boardgames.bgdashboard.controller;

import app.boardgames.bgdashboard.domain.Event;
import app.boardgames.bgdashboard.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/dashboard")
public class EventController {
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
    public ResponseEntity<?> saveEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.saveEvent(event));
    }
}
