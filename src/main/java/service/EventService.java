package service;

import models.Event;
import models.User;
import service.exceptions.EventsOverlappingException;
import service.exceptions.InvalidEventDataException;
import service.exceptions.NoSuchEventException;
import service.exceptions.NoSuchUserException;

import java.util.ArrayList;
import java.util.Date;

public class EventService implements EventServiceInterface {

    private ArrayList<Event> events;

    private static EventService ourInstance = new EventService();

    public static EventService getInstance() {
        return ourInstance;
    }

    private EventService() {
        events = new ArrayList<>();
    }

    /**
     * Considerations how to communicate if there is an overlap of events. Either with an boolean which is returned if it could be added or not or by an exception -> exception would be more clear
     * @param event
     * @throws InvalidEventDataException
     */
    @Override
    public void addEvent(Event event) throws InvalidEventDataException, EventsOverlappingException {
        if(event == null || event.getTitle() == null || event.getTitle().isEmpty() ||
                event.getEndTime() == null || event.getStartTime() == null ||
                event.getParticipants() == null || event.getParticipants().size() < 1){
            throw new InvalidEventDataException("Event is null or data is invalid. Check title, startTime, endTime and participants");
        }
        if(event.getStartTime().after(event.getEndTime()))
            throw new InvalidEventDataException("StartTime of the Event is after EndTime of the Event.");
        if(!checkOverlapParticipants(event)){
            throw new EventsOverlappingException("Event of one user is overlapping with another event of the user.");
        }
        if(eventWithTitle(event.getTitle()) != null){
            throw new InvalidEventDataException("There is already an event with that title. Please choose another title.");
        }
        events.add(event);
    }

    /**
     * @param event
     * @throws NoSuchEventException
     * @throws InvalidEventDataException
     */
    @Override
    public void updateEvent(Event event) throws NoSuchEventException, InvalidEventDataException, EventsOverlappingException {
        if(event == null || event.getTitle() == null || event.getTitle().isEmpty() ||
                event.getEndTime() == null || event.getStartTime() == null ||
                event.getParticipants() == null || event.getParticipants().size() < 1){
            throw new InvalidEventDataException("Event is null or data is invalid. Check title, startTime, endTime and participants");
        }
        if(event.getStartTime().after(event.getEndTime()))
            throw new InvalidEventDataException("StartTime of the Event is after EndTime of the Event.");
        if(!checkOverlapParticipants(event)){
            throw new EventsOverlappingException("Event of one user is overlapping with another event of the user.");
        }

        Event eventToUpdate = eventWithTitle(event.getTitle());
        if( eventToUpdate == null){
            throw new NoSuchEventException("No Event with exactly that Title could be found to update.");
        }
        else {
            events.remove(eventToUpdate);
            events.add(event);
        }
    }

    @Override
    public void deleteEvent(String title) throws NoSuchEventException {
        Event eventToDelete = eventWithTitle(title);
        if(eventToDelete == null) {
            throw new NoSuchEventException("No Event with that title in the list to delete.");
        }
        events.remove(eventToDelete);
    }

    @Override
    public ArrayList<Event> showAllEvents() {
        return events;
    }

    //considerations if it should be checked if the user exists.
    @Override
    public ArrayList<Event> showAllEventsOfUser(User user){
        ArrayList<Event> result = new ArrayList<>();
        for(Event event: events){
            if (event.getParticipants().contains(user)){
                result.add(event);
            }
        }
        return result;
    }

    /**
     * looks for an event in the list with the specific title. The title is used to update the event with exactly that title
     * @param title String to be found as title in all events
     * @return event with the same title or null if there is no event with the same title
     */
    public Event eventWithTitle(String title){
        for(Event event: events){
            if(event.getTitle().equals(title)){
                return event;
            }
        }
        return null;
    }

    /**
     * returns false if there is an overlap for any participant in the new event and true if there is no overlap
     * @param event which is to be checked for all participants if there is an overlap
     * @return false if there is an overlap and true otherwise
     */
    private boolean checkOverlapParticipants(Event event){
        boolean result = true;
        for(User user: event.getParticipants()){
            result = result && checkOverlapUser(user, event);
        }
        return result;
    }

    /**
     * returns false if the new event overlaps with one already existent event in the users calendar.
     * @param user user that is currently checked for overlaps with the eventToAdd
     * @param eventToAdd event that should be added to the calendar (list) if there is no overlap for any user
     * @return false if there is an overlap, true if there is no overlap
     */
    private boolean checkOverlapUser(User user, Event eventToAdd){
        boolean result = true;
        ArrayList<Event> eventsUser = showAllEventsOfUser(user);
        for(Event event: eventsUser){
            result = result && checkOverlapDates(event, eventToAdd);
        }
        return result;
    }

    /**
     * checks if newEvent overlaps with oldEvent
     * @param oldEvent
     * @param newEvent
     * @return false if oldEvent and newEvent overlap, true if not
     */
    private boolean checkOverlapDates(Event oldEvent, Event newEvent){
        return oldEvent.getStartTime().after(newEvent.getEndTime()) || oldEvent.getEndTime().before(newEvent.getStartTime());

    }


    /**
     * for testing instead of showAllEvents()
     * @return list with events
     */
    public ArrayList<Event> getEvents(){
        return events;
    }

    /**
     * for testing
     */
    public void clearArrayList(){
        events = new ArrayList<>();
    }
}
