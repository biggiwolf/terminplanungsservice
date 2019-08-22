package json;

import controller.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Event;
import models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes={Application.class})
@JsonTest
public class JsonEventTest {

    Event testEvent;

    User testUser1 = new User(1,"Adam", "adam@company.com");
    User testUser2 = new User(2, "Anna");

    //year, month, dayOfMonth, hourOfDay, minute
    Calendar calStartDate1 = new GregorianCalendar(2019, Calendar.MAY, 3, 15, 0);

    Calendar calEndDate1 = new GregorianCalendar(2019, Calendar.MAY, 3, 17,0);

    List<User> participants1;
    List<User> participants2;
    List<User> participants3;

    @Before
    public void setUp(){

        participants1 = new ArrayList<>();
        participants1.add(testUser1);
        participants1.add(testUser2);

        calStartDate1.setTimeZone(TimeZone.getTimeZone("GMT"));
        calEndDate1.setTimeZone(TimeZone.getTimeZone("GMT"));

        Date startDate1 = calStartDate1.getTime();
        Date endDate1 = calEndDate1.getTime();

        testEvent = new Event(participants1, startDate1, endDate1, "BirthdayMeeting");

    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testEqualEvents() throws IOException {
        Event eventIn = objectMapper.readValue(new File("src/test/resources/event.json"), Event.class);
        Assert.assertEquals(testEvent, eventIn);
    }

}
