package service;

import models.Event;
import models.User;
import service.exceptions.InvalidEventDataException;
import service.exceptions.NoSuchEventException;
import service.exceptions.NoSuchUserException;

import java.util.ArrayList;

public interface EventServiceInterface {

    public void addEvent(Event event) throws InvalidEventDataException;

    public void updateEvent(Event event) throws NoSuchEventException, InvalidEventDataException;

    public void deleteEvent(Event event) throws NoSuchEventException;

    public ArrayList<Event> showAllEvents();

    public ArrayList<Event> showAllEventsOfUser(User user) throws NoSuchUserException;

}
