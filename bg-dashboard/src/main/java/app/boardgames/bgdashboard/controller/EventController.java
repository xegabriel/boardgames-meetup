package app.boardgames.bgdashboard.controller;

import app.boardgames.bgdashboard.domain.Event;
import app.boardgames.bgdashboard.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "api/dashboard")
public class EventController {
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
    public ResponseEntity<?> saveEvent(@RequestBody Event event, HttpServletRequest request) {
        return ResponseEntity.ok(eventService.saveEvent(request.getHeader("email"), event));
    }

    @RequestMapping(value = "/stopEventRegistration/{eventTitle}/{finalGame}", method = RequestMethod.PUT)
    public ResponseEntity<?> stopEventRegistration(@PathVariable String eventTitle, @PathVariable String finalGame, HttpServletRequest request) {
        return ResponseEntity.ok(eventService.stopEventRegistration(request.getHeader("email"), eventTitle, finalGame));
    }

    @RequestMapping(value = "/updateEvent", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEvent(@RequestBody Event event, HttpServletRequest request) {
        return ResponseEntity.ok(eventService.updateEvent(request.getHeader("email"), event));
    }


}
