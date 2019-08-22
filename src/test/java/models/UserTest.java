package models;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void testCreateUser(){
        User user1 = new User(1,"Amy");
        User user2 = new User(2, "Anne");
        Assert.assertEquals(1, user1.getId());
        Assert.assertEquals("Amy", user1.getName());
        Assert.assertEquals(2, user2.getId());
        Assert.assertEquals("Anne", user2.getName());
        User user3 = new User(8, "Adam", "adam@company.com");
    }

    @Test
    public void testChangeNameUser(){
        User user1 = new User(3, "Alan");
        Assert.assertEquals(3, user1.getId());
        Assert.assertEquals("Alan", user1.getName());
        user1.setName("Alex");
        Assert.assertEquals("Alex", user1.getName());
    }

    @Test
    public void testWrongId(){
        User user1 = new User(4, "Brian");
        Assert.assertNotEquals(2, user1.getId());
    }

    @Test
    public void compareTwoUnequalUsersWithoutMail(){
        User user1 = new User(5, "Andy");
        User user2 = new User(6, "Amanda");
        Assert.assertFalse(user1.equals(user2));
    }

    @Test
    public void compareTwoUnequalUsersWithMail(){
        User user1 = new User(5, "Andy", "andy@gmail.com");
        User user2 = new User(5, "Andy", "andy@company.com");
        Assert.assertFalse(user1.equals(user2));
    }

    @Test
    public void compareTwoEqualUsers(){
        User user1 = new User(7, "Andy");
        User user2 = new User(7, "Andy");
        Assert.assertTrue(user2.equals(user2));
    }

}