package controller;

import models.Event;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import service.EventService;
import service.UserService;
import service.exceptions.*;

import java.util.List;

/**
 * takes care of requests which start with /events in the URI
 * contains a very simple method (/greetings) to test if the service works
 */
@RestController
@RequestMapping("/events")
public class EventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @RequestMapping("/greeting")
    public String index(){
        LOGGER.debug("/ MAPPING");
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/all")
    public List<Event> allEvents(){
        LOGGER.debug("/events/all mapping");
        return EventService.getInstance().showAllEvents();
    }

    @GetMapping("/all/{id}")
    public List<Event> showAllEventsOfOneUser(@PathVariable long id){
        LOGGER.debug("/events/{id} GET mapping");
        return EventService.getInstance().showAllEventsOfUser(UserService.getInstance().userWithId(id));
    }

    @PutMapping("/{title}")
    public void addOrReplaceEvent(@RequestBody Event event, @PathVariable("title") String title){
        LOGGER.debug("/events/{title} PUT mapping");
        try {
            if(EventService.getInstance().eventWithTitle(title) == null){
                EventService.getInstance().addEvent(event);
            }
            else {
                EventService.getInstance().updateEvent(event);
            }
        } catch (InvalidEventDataException | NoSuchEventException | EventsOverlappingException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{title}")
    public void deleteEvent(@PathVariable String title){
        LOGGER.debug("/events/{title} DELETE mapping");
        try {
            EventService.getInstance().deleteEvent(title);
        } catch (NoSuchEventException e) {
            e.printStackTrace();
        }
    }

}
