package service;

import models.User;
import service.exceptions.InvalidUserDataException;
import service.exceptions.NoSuchUserException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserService implements UserServiceInterface{

    private ArrayList<User> users;

    private static UserService ourInstance = new UserService();

    public static UserService getInstance() {
        return ourInstance;
    }

    private UserService() {
        users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) throws InvalidUserDataException{

    }

    @Override
    public void updateUser(User user) throws NoSuchUserException, InvalidUserDataException {

    }

    @Override
    public void deleteUser(long id) throws NoSuchUserException, InvalidUserDataException {

    }

    @Override
    public User showOneUser(long id) throws NoSuchUserException {
        return null;
    }

    @Override
    public ArrayList<User> showAllUsers() {
        return null;
    }

    public ArrayList<User> getUsers(){
        return users;
    }
}
