package service;

import models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.exceptions.InvalidUserDataException;
import service.exceptions.NoSuchUserException;
import java.util.ArrayList;

public class UserServiceTest {

    User user1 = new User(1,"Amy");
    User user2 = new User(2, "Anne");
    User user3 = new User(8, "Adam", "adam@company.com");
    User user4 = new User(5, "Alan", "mail@adam.com");
    User user5 = new User();
    User newUser1 = new User(1, "Amy", "amy@amy.com");

    @Before
    public void setUp() throws InvalidUserDataException {
        UserService.getInstance().clearArrayList();
        UserService.getInstance().addUser(user1);
        UserService.getInstance().addUser(user3);
        user5.setName("Alex");
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
