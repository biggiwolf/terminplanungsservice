package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testCreateUser(){
        User user1 = new User(1,"Amy");
        User user2 = new User(2, "Anne");
        assertEquals(1, user1.getId());
        assertEquals("Amy", user1.getName());
        assertEquals(2, user2.getId());
        assertEquals("Anne", user2.getName());
        User user3 = new User(8, "Adam", "adam@company.com");
    }

    @Test
    public void testChangeNameUser(){
        User user1 = new User(3, "Alan");
        assertEquals(3, user1.getId());
        assertEquals("Alan", user1.getName());
        user1.setName("Alex");
        assertEquals("Alex", user1.getName());
    }

    @Test
    public void testWrongId(){
        User user1 = new User(4, "Brian");
        assertNotEquals(2, user1.getId());
    }

    @Test
    public void compareTwoUnequalUsersWithoutMail(){
        User user1 = new User(5, "Andy");
        User user2 = new User(6, "Amanda");
        assertFalse(user1.equals(user2));
    }

    @Test
    public void compareTwoUnequalUsersWithMail(){
        User user1 = new User(5, "Andy", "andy@gmail.com");
        User user2 = new User(5, "Andy", "andy@company.com");
        assertFalse(user1.equals(user2));
    }

    @Test
    public void compareTwoEqualUsers(){
        User user1 = new User(7, "Andy");
        User user2 = new User(7, "Andy");
        assertTrue(user2.equals(user2));
    }

}