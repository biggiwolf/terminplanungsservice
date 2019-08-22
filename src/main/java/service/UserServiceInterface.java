package service;

import models.User;
import service.exceptions.InvalidUserDataException;
import service.exceptions.NoSuchUserException;

import java.util.ArrayList;

public interface UserServiceInterface {

    /**
     * add user to the collection in the user management. In this case the service
     * @param user
     */
    public void addUser(User user) throws InvalidUserDataException;

    /**
     * update user with the user id of user with the value of
     * @param user
     */
    public void updateUser(User user) throws NoSuchUserException, InvalidUserDataException;

    public void deleteUser(long id) throws NoSuchUserException, InvalidUserDataException;

    public User showOneUser(long id) throws NoSuchUserException, InvalidUserDataException;

    public ArrayList<User> showAllUsers();

}
