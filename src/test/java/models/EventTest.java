package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

public class EventTest {

    //year, month, dayOfMonth, hourOfDay, minute
    Calendar calStartDate1 = new GregorianCalendar(2019, Calendar.DECEMBER, 1, 12, 0);
    Calendar calStartDate2 = new GregorianCalendar(2019, Calendar.JANUARY, 1, 13, 0);
    Calendar calStartDate3 = new GregorianCalendar(2019, Calendar.JUNE, 1, 16, 0);
    Date startDate1 = calStartDate1.getTime();
    Date startDate2 = calStartDate2.getTime();
    Date startDate3 = calStartDate3.getTime();

    Calendar calEndDate1 = new GregorianCalendar(2019, Calendar.DECEMBER, 1, 13,0);
    Calendar calEndDate2 = new GregorianCalendar(2019, Calendar.JANUARY, 1, 16,0);
    Calendar calEndDate3 = new GregorianCalendar(2019, Calendar.JUNE, 1, 19,0);
    Date endDate1 = calEndDate1.getTime();
    Date endDate2 = calEndDate2.getTime();
    Date endDate3 = calEndDate3.getTime();

    User user1 = new User(1, "Harry");
    User user2 = new User(2, "Berry");
    User user3 = new User(3, "Mary");
    User user4 = new User(4, "Cherry");

    List<User> participants1;
    List<User> participants2;
    List<User> participants3;

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
        Assert.assertEquals(participants1, event1.getParticipants());
        Assert.assertEquals(startDate1, event1.getStartTime());
        Assert.assertEquals(endDate1, event1.getEndTime());
        Assert.assertEquals("Meeting1", event1.getTitle());
    }

    @Test
    public void testSetParticipants() {
        Event event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        Assert.assertEquals(participants1, event1.getParticipants());
        Assert.assertEquals(startDate1, event1.getStartTime());
        Assert.assertEquals(endDate1, event1.getEndTime());
        Assert.assertEquals("Meeting1", event1.getTitle());

        event1.setParticipants(participants2);
        Assert.assertEquals(participants2, event1.getParticipants());
        Assert.assertEquals(startDate1, event1.getStartTime());
        Assert.assertEquals(endDate1, event1.getEndTime());
        Assert.assertEquals("Meeting1", event1.getTitle());
    }

    @Test
    public void testSetStartTime() {
        Event event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        Assert.assertEquals(participants1, event1.getParticipants());
        Assert.assertEquals(startDate1, event1.getStartTime());
        Assert.assertEquals(endDate1, event1.getEndTime());
        Assert.assertEquals("Meeting1", event1.getTitle());

        event1.setStartTime(startDate2);
        Assert.assertEquals(participants1, event1.getParticipants());
        Assert.assertEquals(startDate2, event1.getStartTime());
        Assert.assertEquals(endDate1, event1.getEndTime());
        Assert.assertEquals("Meeting1", event1.getTitle());
    }

    @Test
    public void testSetEndTime() {
        Event event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        Assert.assertEquals(participants1, event1.getParticipants());
        Assert.assertEquals(startDate1, event1.getStartTime());
        Assert.assertEquals(endDate1, event1.getEndTime());
        Assert.assertEquals("Meeting1", event1.getTitle());

        event1.setEndTime(endDate3);
        Assert.assertEquals(participants1, event1.getParticipants());
        Assert.assertEquals(startDate1, event1.getStartTime());
        Assert.assertEquals(endDate3, event1.getEndTime());
        Assert.assertEquals("Meeting1", event1.getTitle());
    }

    @Test
    public void testSetTitle() {
        Event event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        Assert.assertEquals(participants1, event1.getParticipants());
        Assert.assertEquals(startDate1, event1.getStartTime());
        Assert.assertEquals(endDate1, event1.getEndTime());
        Assert.assertEquals("Meeting1", event1.getTitle());

        event1.setTitle("Birthday");
        Assert.assertEquals(participants1, event1.getParticipants());
        Assert.assertEquals(startDate1, event1.getStartTime());
        Assert.assertEquals(endDate1, event1.getEndTime());
        Assert.assertEquals("Birthday", event1.getTitle());
    }

    @Test
    public void testTwoEqualEvents() {
        Event event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        Event event2 = new Event(participants1, startDate1, endDate1, "Meeting1");

        Assert.assertTrue(event1.equals(event2));
    }

    @Test
    public void testTwoUnequalEvents() {
        Event event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        Event event2 = new Event(participants1, startDate1, endDate1, "Birthday");

        Assert.assertFalse(event1.equals(event2));
    }
}
