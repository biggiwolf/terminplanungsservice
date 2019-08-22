package json;

import app.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={Application.class})
@JsonTest
public class JsonEventTest {



}
