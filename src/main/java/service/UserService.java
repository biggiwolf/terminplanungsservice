package service;

import models.User;
import service.exceptions.NoSuchUserException;

import java.util.ArrayList;

public class UserService implements UserServiceInterface{

    ArrayList<User> users;

    private static UserService ourInstance = new UserService();

    public static UserService getInstance() {
        return ourInstance;
    }

    private UserService() {
        users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) throws NoSuchUserException {

    }

    @Override
    public void deleteUser(long id) throws NoSuchUserException {

    }

    @Override
    public void showOneUser(long id) throws NoSuchUserException {

    }

    @Override
    public void showAllUsers() {

    }
}
