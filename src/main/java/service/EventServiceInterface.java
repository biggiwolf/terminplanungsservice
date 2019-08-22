package service;

import models.Event;
import models.User;
import service.exceptions.EventsOverlappingException;
import service.exceptions.InvalidEventDataException;
import service.exceptions.NoSuchEventException;
import service.exceptions.NoSuchUserException;

import java.util.ArrayList;

public interface EventServiceInterface {

    public void addEvent(Event event) throws InvalidEventDataException, EventsOverlappingException;

    public void updateEvent(Event event) throws NoSuchEventException, InvalidEventDataException, EventsOverlappingException;

    public void deleteEvent(String title) throws NoSuchEventException;

    public ArrayList<Event> showAllEvents();

    public ArrayList<Event> showAllEventsOfUser(User user);

}
