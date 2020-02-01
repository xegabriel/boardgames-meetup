package app.boardgames.bgdashboard.controller;

import app.boardgames.bgdashboard.domain.Event;
import app.boardgames.bgdashboard.services.EventService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/dashboard")
public class EventController {
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
    public ResponseEntity<?> saveEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.saveEvent(event));
    }

    @RequestMapping(value = "/stopEventRegistration/{eventTitle}", method = RequestMethod.PUT)
    public ResponseEntity<?> stopEventRegistration(@PathVariable String eventTitle) {
        return ResponseEntity.ok(eventService.stopEventRegistration(eventTitle));
    }

    @RequestMapping(value = "/updateEvent", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(event));
    }
}
