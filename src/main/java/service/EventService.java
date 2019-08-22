package service;

import models.Event;
import models.User;
import service.exceptions.InvalidEventDataException;
import service.exceptions.NoSuchEventException;
import service.exceptions.NoSuchUserException;

import java.util.ArrayList;

public class EventService implements EventServiceInterface {
    private static EventService ourInstance = new EventService();

    public static EventService getInstance() {
        return ourInstance;
    }

    private EventService() {
    }

    @Override
    public void addEvent(Event event) throws InvalidEventDataException {

    }

    @Override
    public void updateEvent(Event event) throws NoSuchEventException, InvalidEventDataException {

    }

    @Override
    public void deleteEvent(Event event) throws NoSuchEventException {

    }

    @Override
    public ArrayList<Event> showAllEvents() {
        return null;
    }

    @Override
    public ArrayList<Event> showAllEventsOfUser(User user) throws NoSuchUserException {
        return null;
    }
}
