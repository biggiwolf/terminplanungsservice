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
        if(user == null || user.getId() == 0 || user.getName() == null){
            throw new InvalidUserDataException("User is null or id or name are not existent but are obligatory.");
        }
        if(userWithId(user.getId()) != null){
            throw new InvalidUserDataException("There is already a user with the same id. Please choose another id.");
        }
        else
            users.add(user);
    }

    @Override
    public void updateUser(User user) throws NoSuchUserException, InvalidUserDataException {
        if(user == null || user.getId() == 0 || user.getName() == null){
            throw new InvalidUserDataException("User is null or id or name are not existent but are obligatory.");
        }
        if(!this.containsUserWithId(user.getId())){
            throw new NoSuchUserException("User with the given id not found in the collection.");
        }
        User oldUser = showOneUser(user.getId());
        users.remove(oldUser);
        users.add(user);
    }

    private User userWithId(long id){
        for(User user: users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    //TODO check all events of one user to remove them if no participant is left
    @Override
    public void deleteUser(long id) throws NoSuchUserException, InvalidUserDataException {
        User userToRemove = null;
        if(!this.containsUserWithId(id)){
            throw new NoSuchUserException("User with the given id not found in the collection.");
        }
        else {
            for(User user: users){
                if(user.getId() == id){
                    userToRemove = user;
                }
            }
        }
        users.remove(userToRemove);
    }

    @Override
    public User showOneUser(long id) throws NoSuchUserException {
        if(!this.containsUserWithId(id)){
            throw new NoSuchUserException("User with the given id not found in the collection.");
        }
        else{
            for(User user: users){
                if (user.getId() == id)
                    return user;
            }
        }
        return null;
    }

    @Override
    public ArrayList<User> showAllUsers() {
        return users;
    }



    private boolean containsUserWithId(long id){
        for(User user: users){
            if(user.getId() == id)
                return true;
        }
        return false;
    }

    /**
     * for testing
     */
    public ArrayList<User> getUsers(){
        return users;
    }

    /**
     * for testing
     */
    public void clearArrayList(){
        users = new ArrayList<>();
    }
}
