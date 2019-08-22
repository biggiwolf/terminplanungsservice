package controller;

import models.Event;
import models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import service.EventService;
import service.UserService;
import service.exceptions.EventsOverlappingException;
import service.exceptions.InvalidEventDataException;
import service.exceptions.InvalidUserDataException;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class EventControllerTest {


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
    List<User> participants4;

    Event event1;
    Event event2;
    Event event3;
    Event event4;
    Event event5;

    User user6 = new User(6,"Amy");
    User user7 = new User(7, "Anne");
    User user8 = new User(8, "Adam", "adam@company.com");
    User user9 = new User(9, "Alan", "mail@adam.com");
    User user11 = new User(11, "Aron", "aron@company.com");
    User user12 = new User(7, "Anna", "anna@company.com");

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() throws EventsOverlappingException, InvalidEventDataException, InvalidUserDataException {
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

        participants4 = new ArrayList<>();
        participants4.add(user5);

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
        Date endDate3 = calEndDate3.getTime();
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

        EventService.getInstance().addEvent(event1);
        EventService.getInstance().addEvent(event2);
    }

    @Test
    public void getGreetings() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/events/greeting").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));}

    @Test
    public void getAllEvents() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/events/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
    }

    @Test
    public void getAllEventsOfOneUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/events/all/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
    }

    @Test
    public void addOneEvent(){

    }

    @Test
    public void updateOneEvent(){

    }

    @Test
    public void deleteOneEvent(){

    }

}
