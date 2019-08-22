package service;

import models.Event;
import models.User;
import org.junit.Before;
import org.junit.Test;
import service.exceptions.InvalidEventDataException;
import service.exceptions.NoSuchEventException;
import service.exceptions.NoSuchUserException;

import java.util.*;
import static org.junit.Assert.*;

public class EventServiceTest {

    //year, month, dayOfMonth, hourOfDay, minute
    Calendar calStartDate1 = new GregorianCalendar(2019, Calendar.DECEMBER, 1, 12, 0);
    Calendar calStartDate2 = new GregorianCalendar(2019, Calendar.JANUARY, 1, 13, 0);
    Calendar calStartDate3 = new GregorianCalendar(2019, Calendar.JUNE, 1, 16, 0);
    Calendar calStartDate4 = new GregorianCalendar(2019, Calendar.DECEMBER, 1, 12, 30);

    Calendar calEndDate1 = new GregorianCalendar(2019, Calendar.DECEMBER, 1, 13,0);
    Calendar calEndDate2 = new GregorianCalendar(2019, Calendar.JANUARY, 1, 16,0);
    Calendar calEndDate3 = new GregorianCalendar(2019, Calendar.JUNE, 1, 19,0);
    Calendar calEndDate4 = new GregorianCalendar(2019, Calendar.DECEMBER, 1, 13,30);

    User user1 = new User(1, "Harry");
    User user2 = new User(2, "Berry");
    User user3 = new User(3, "Mary");
    User user4 = new User(4, "Cherry");
    User user5 = new User(5, "Adam", "adam@notinthelist.com");

    List<User> participants1;
    List<User> participants2;
    List<User> participants3;

    Event event1;
    Event event2;
    Event event3;
    Event event4;
    Event event5;

    Date endDate3;

    @Before
    public void setUp() throws InvalidEventDataException {

        EventService.getInstance().clearArrayList();
        UserService.getInstance().clearArrayList();

        participants1 = new ArrayList<>();
        participants1.add(user1);
        participants1.add(user4);

        participants2 = new ArrayList<>();
        participants2.add(user2);

        participants3 = new ArrayList<>();
        participants3.add(user4);
        participants3.add(user3);
        participants3.add(user2);
        participants3.add(user1);

        calStartDate1.setTimeZone(TimeZone.getTimeZone("GMT"));
        calEndDate1.setTimeZone(TimeZone.getTimeZone("GMT"));
        calStartDate2.setTimeZone(TimeZone.getTimeZone("GMT"));
        calEndDate2.setTimeZone(TimeZone.getTimeZone("GMT"));
        calStartDate3.setTimeZone(TimeZone.getTimeZone("GMT"));
        calEndDate3.setTimeZone(TimeZone.getTimeZone("GMT"));
        calStartDate4.setTimeZone(TimeZone.getTimeZone("GMT"));
        calEndDate4.setTimeZone(TimeZone.getTimeZone("GMT"));

        Date startDate1 = calStartDate1.getTime();
        Date startDate2 = calStartDate2.getTime();
        Date startDate3 = calStartDate3.getTime();
        Date startDate4 = calStartDate4.getTime();

        Date endDate1 = calEndDate1.getTime();
        Date endDate2 = calEndDate2.getTime();
        endDate3 = calEndDate3.getTime();
        Date endDate4 = calEndDate4.getTime();

        event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        event2 = new Event(participants3, startDate2, endDate2, "Meeting2");
        event3 = new Event(participants2, startDate3, endDate3, "Meeting3");
        event4 = new Event(participants1, startDate4, endDate4, "Meeting4");

        EventService.getInstance().addEvent(event1);
        EventService.getInstance().addEvent(event2);

        event5 = new Event();
        event5.setParticipants(participants2);
        event5.setEndTime(endDate4);
        event5.setTitle("Meeting5");

    }

    @Test
    public void testAddEventOK() throws InvalidEventDataException {
        assertFalse(EventService.getInstance().getEvents().contains(event3));
        EventService.getInstance().addEvent(event3);
        assertTrue(EventService.getInstance().getEvents().contains(event3));
    }

    @Test(expected = InvalidEventDataException.class)
    public void testAddEventThrowsException() throws InvalidEventDataException {
        assertFalse(EventService.getInstance().getEvents().contains(event5));
        EventService.getInstance().addEvent(event5);
    }

    @Test
    public void updateEventOK() throws InvalidEventDataException, NoSuchEventException {
        assertTrue(EventService.getInstance().getEvents().contains(event2));
        Event event6 = new Event();
        event6.setParticipants(event2.getParticipants());
        event6.setEndTime(event2.getEndTime());
        event6.setTitle(event2.getTitle());
        event6.setStartTime(endDate3);
        EventService.getInstance().updateEvent(event6);
        assertFalse(EventService.getInstance().getEvents().contains(event2));
        assertTrue(EventService.getInstance().getEvents().contains(event6));
    }

    @Test(expected = InvalidEventDataException.class)
    public void updateEventThrowsException() throws InvalidEventDataException, NoSuchEventException {
        assertTrue(EventService.getInstance().getEvents().contains(event2));
        event2.setTitle(null);
        EventService.getInstance().updateEvent(event2);
    }

    @Test(expected = InvalidEventDataException.class)
    public void updateEventTitleSame() throws InvalidEventDataException, NoSuchEventException {
        assertTrue(EventService.getInstance().getEvents().contains(event2));
        event2.setTitle("Meeting 1");
        EventService.getInstance().updateEvent(event2);
    }

    @Test
    public void deleteEventOK() throws NoSuchEventException {
        assertTrue(EventService.getInstance().getEvents().contains(event2));
        EventService.getInstance().deleteEvent(event2);
        assertFalse(EventService.getInstance().getEvents().contains(event2));
    }

    @Test(expected = NoSuchEventException.class)
    public void deleteEventThrowsException() throws NoSuchEventException {
        EventService.getInstance().deleteEvent(event3);
    }

    @Test
    public void showAllEvents(){
        ArrayList<Event> result = EventService.getInstance().showAllEvents();
        assertEquals(2, result.size());
    }

    @Test
    public void showAllEventsOfOneUserOK() throws NoSuchUserException {
        ArrayList<Event> result = EventService.getInstance().showAllEventsOfUser(user1);
        assertEquals(2, result.size());
    }

    @Test(expected = NoSuchEventException.class)
    public void showAllEventsOfOneUserThrowsException() throws NoSuchUserException {
        ArrayList<Event> result = EventService.getInstance().showAllEventsOfUser(user5);
    }

}
