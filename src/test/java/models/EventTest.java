package models;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class EventTest {

    //year, month, dayOfMonth, hourOfDay, minute
    Calendar startDate1 = new GregorianCalendar(2019, Calendar.DECEMBER, 1, 12, 0);
    Calendar startDate2 = new GregorianCalendar(2019, Calendar.JANUARY, 1, 13, 0);
    Calendar startDate3 = new GregorianCalendar(2019, Calendar.JUNE, 1, 16, 0);

    Calendar endDate1 = new GregorianCalendar(2019, Calendar.DECEMBER, 1, 13,0);
    Calendar endDate2 = new GregorianCalendar(2019, Calendar.JANUARY, 1, 16,0);
    Calendar endDate3 = new GregorianCalendar(2019, Calendar.JUNE, 1, 19,0);

    User user1 = new User(1, "Harry");
    User user2 = new User(2, "Berry");
    User user3 = new User(3, "Mary");
    User user4 = new User(4, "Cherry");

    ArrayList<User> participants1;
    ArrayList<User> participants2;
    ArrayList<User> participants3;

    @Before
    public void setUp(){

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

    }


    @Test
    public void testCreateEvent(){
        Event event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        assertEquals(participants1, event1.getParticipants());
        assertEquals(startDate1, event1.getStartTime());
        assertEquals(endDate1, event1.getEndTime());
        assertEquals("Meeting1", event1.getTitle());
    }

    @Test
    public void testSetParticipants() {
        Event event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        assertEquals(participants1, event1.getParticipants());
        assertEquals(startDate1, event1.getStartTime());
        assertEquals(endDate1, event1.getEndTime());
        assertEquals("Meeting1", event1.getTitle());

        event1.setParticipants(participants2);
        assertEquals(participants2, event1.getParticipants());
        assertEquals(startDate1, event1.getStartTime());
        assertEquals(endDate1, event1.getEndTime());
        assertEquals("Meeting1", event1.getTitle());
    }

    @Test
    public void testSetStartTime() {
        Event event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        assertEquals(participants1, event1.getParticipants());
        assertEquals(startDate1, event1.getStartTime());
        assertEquals(endDate1, event1.getEndTime());
        assertEquals("Meeting1", event1.getTitle());

        event1.setStartTime(startDate2);
        assertEquals(participants1, event1.getParticipants());
        assertEquals(startDate2, event1.getStartTime());
        assertEquals(endDate1, event1.getEndTime());
        assertEquals("Meeting1", event1.getTitle());
    }

    @Test
    public void testSetEndTime() {
        Event event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        assertEquals(participants1, event1.getParticipants());
        assertEquals(startDate1, event1.getStartTime());
        assertEquals(endDate1, event1.getEndTime());
        assertEquals("Meeting1", event1.getTitle());

        event1.setEndTime(endDate3);
        assertEquals(participants1, event1.getParticipants());
        assertEquals(startDate1, event1.getStartTime());
        assertEquals(endDate3, event1.getEndTime());
        assertEquals("Meeting1", event1.getTitle());
    }

    @Test
    public void testSetTitle() {
        Event event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        assertEquals(participants1, event1.getParticipants());
        assertEquals(startDate1, event1.getStartTime());
        assertEquals(endDate1, event1.getEndTime());
        assertEquals("Meeting1", event1.getTitle());

        event1.setTitle("Birthday");
        assertEquals(participants1, event1.getParticipants());
        assertEquals(startDate1, event1.getStartTime());
        assertEquals(endDate1, event1.getEndTime());
        assertEquals("Birthday", event1.getTitle());
    }

    @Test
    public void testTwoEqualEvents() {
        Event event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        Event event2 = new Event(participants1, startDate1, endDate1, "Meeting1");

        assertTrue(event1.equals(event2));
    }

    @Test
    public void testTwoUnequalEvents() {
        Event event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        Event event2 = new Event(participants1, startDate1, endDate1, "Birthday");

        assertFalse(event1.equals(event2));
    }
}
