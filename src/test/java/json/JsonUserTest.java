package json;

import controller.Application;

import models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.json.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.json.*;
import java.io.File;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={Application.class})
@JsonTest
public class JsonUserTest {

/*
    User testUser = new User(1,"Adam", "adam@company.com");

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test() throws IOException {
        User userIn = objectMapper.readValue(new File("src/main/java/test/resources/user.json"), User.class);
        assertEquals(userIn, testUser);
    }
*/

    @Autowired
    private JacksonTester<User> json;

    @Test
    public void testSerializeFile() throws Exception {
        User user = new User(1, "Adam", "adam@company.com");
        assertThat(this.json.write(user)).isEqualToJson(new File("src/test/resources/user.json"));
    }

    @Test
    public void testSerializePath() throws Exception {
        User user = new User(1, "Adam", "adam@company.com");
        assertThat(this.json.write(user)).hasJsonPathStringValue("@.name");
        assertThat(this.json.write(user)).extractingJsonPathStringValue("@.name").isEqualTo("Adam");
    }

    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"id\": 1,\"name\":\"Adam\",\"mail\":\"adam@company.com\"}";
        assertThat(this.json.parse(content)).isEqualTo(new User(1, "Adam", "adam@company.com"));
        assertThat(this.json.parseObject(content).getName()).isEqualTo("Adam");
        assertThat(this.json.parseObject(content).getId()).isEqualTo(1);
        assertThat(this.json.parseObject(content).getMail()).isEqualTo("adam@company.com");
    }

}
