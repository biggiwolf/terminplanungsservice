package service;

import models.Event;
import models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.exceptions.EventsOverlappingException;
import service.exceptions.InvalidEventDataException;
import service.exceptions.InvalidUserDataException;
import service.exceptions.NoSuchUserException;

import java.util.*;

public class UserServiceTest {

    //year, month, dayOfMonth, hourOfDay, minute
    Calendar calStartDate1 = new GregorianCalendar(2019, Calendar.DECEMBER, 1, 12, 0);
    Calendar calStartDate2 = new GregorianCalendar(2019, Calendar.JANUARY, 1, 13, 0);

    Calendar calEndDate1 = new GregorianCalendar(2019, Calendar.DECEMBER, 1, 13,0);
    Calendar calEndDate2 = new GregorianCalendar(2019, Calendar.JANUARY, 1, 16,0);

    List<User> participants1;
    List<User> participants2;

    Event event1;
    Event event2;
    Event event3;
    Event event4;
    Event event5;

    Date endDate3;

    User user1 = new User(1,"Amy");
    User user2 = new User(2, "Anne");
    User user3 = new User(8, "Adam", "adam@company.com");
    User user4 = new User(5, "Alan", "mail@adam.com");
    User user5 = new User();
    User newUser1 = new User(1, "Amy", "amy@amy.com");

    @Before
    public void setUp() throws InvalidUserDataException, EventsOverlappingException, InvalidEventDataException {

        EventService.getInstance().clearArrayList();
        UserService.getInstance().clearArrayList();

        participants1 = new ArrayList<>();
        participants1.add(user1);
        participants1.add(user2);

        participants2 = new ArrayList<>();
        participants2.add(user2);

        calStartDate1.setTimeZone(TimeZone.getTimeZone("GMT"));
        calEndDate1.setTimeZone(TimeZone.getTimeZone("GMT"));
        calStartDate2.setTimeZone(TimeZone.getTimeZone("GMT"));
        calEndDate2.setTimeZone(TimeZone.getTimeZone("GMT"));

        Date startDate1 = calStartDate1.getTime();
        Date startDate2 = calStartDate2.getTime();
        Date endDate1 = calEndDate1.getTime();
        Date endDate2 = calEndDate2.getTime();

        event1 = new Event(participants1, startDate1, endDate1, "Meeting1");
        event2 = new Event(participants2, startDate2, endDate2, "Meeting2");

        EventService.getInstance().addEvent(event1);
        EventService.getInstance().addEvent(event2);

        UserService.getInstance().clearArrayList();
        UserService.getInstance().addUser(user1);
        UserService.getInstance().addUser(user3);
        user5.setName("Alex");
    }

    /**
     * deletes a user which is participant and two events and expects the two events to update
     * @throws NoSuchUserException
     * @throws InvalidUserDataException
     */
    @Test
    public void deleteUserAndEvent() throws NoSuchUserException, InvalidUserDataException {
        UserService.getInstance().addUser(user2);
        Assert.assertTrue(EventService.getInstance().getEvents().size() == 2);
        UserService.getInstance().deleteUser(2);
        Assert.assertTrue(EventService.getInstance().getEvents().size() == 1);
        Assert.assertTrue(EventService.getInstance().showAllEventsOfUser(user1).get(0).getParticipants().size() == 1);
    }


    @Test
    public void testAddUserOK() throws InvalidUserDataException {
        Assert.assertEquals(2,UserService.getInstance().getUsers().size());
        UserService.getInstance().addUser(user2);
        Assert.assertEquals(3, UserService.getInstance().getUsers().size());
        Assert.assertTrue(UserService.getInstance().getUsers().contains(user2));
    }

    @Test(expected = InvalidUserDataException.class)
    public void testAddUserThrowsException() throws InvalidUserDataException {
        UserService.getInstance().addUser(user5);
    }

    @Test
    public void updateUserOK() throws NoSuchUserException, InvalidUserDataException {
        Assert.assertTrue(UserService.getInstance().getUsers().contains(user1));
        UserService.getInstance().updateUser(newUser1);
        Assert.assertFalse(UserService.getInstance().getUsers().contains(user1));
        Assert.assertTrue(UserService.getInstance().getUsers().contains(newUser1));
    }

    @Test(expected = NoSuchUserException.class)
    public void updateUserThrowsException() throws NoSuchUserException, InvalidUserDataException {
        UserService.getInstance().updateUser(user4);
    }

    @Test
    public void deleteUserOK() throws NoSuchUserException, InvalidUserDataException {
        Assert.assertTrue(UserService.getInstance().getUsers().contains(user1));
        UserService.getInstance().deleteUser(user1.getId());
        Assert.assertFalse(UserService.getInstance().getUsers().contains(user1));
    }

    @Test(expected = NoSuchUserException.class)
    public void deleteUserThrowsException() throws NoSuchUserException, InvalidUserDataException {
        Assert.assertTrue(UserService.getInstance().getUsers().contains(user1));
        UserService.getInstance().deleteUser(99);
    }

    @Test
    public void showOneUserOK() throws NoSuchUserException {
        Assert.assertTrue(UserService.getInstance().getUsers().contains(user1));
        User showUser1 = UserService.getInstance().showOneUser(user1.getId());
        Assert.assertEquals(showUser1, user1);
    }

    @Test(expected = NoSuchUserException.class)
    public void showOneUserThrowsException() throws NoSuchUserException {
        Assert.assertFalse(UserService.getInstance().getUsers().contains(user2));
        UserService.getInstance().showOneUser(user2.getId());
    }

    @Test
    public void showAllUsers(){
        ArrayList<User> result = UserService.getInstance().showAllUsers();
        ArrayList<User> comparison = new ArrayList<>();
        comparison.add(user1);
        comparison.add(user3);
        Assert.assertEquals(result, comparison);
    }

}
