package controller;

import models.User;
import org.springframework.web.bind.annotation.*;
import service.EventService;
import service.UserService;
import service.exceptions.InvalidUserDataException;
import service.exceptions.NoSuchUserException;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> allUsers(){
        return UserService.getInstance().showAllUsers();
    }

    @GetMapping("/users/{id}")
    public User showOneUser(@PathVariable long id){
        try {
            return UserService.getInstance().showOneUser(id);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/users/{id}")
    public void addOrReplaceUser(@RequestBody User user, @PathVariable long id){
        try {
            if(UserService.getInstance().userWithId(id) == null) {
                UserService.getInstance().addUser(user);
            }
            else {
                UserService.getInstance().updateUser(user);
            }
        } catch (InvalidUserDataException | NoSuchUserException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@RequestBody User user, @PathVariable long id){
        try {
            UserService.getInstance().deleteUser(id);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        } catch (InvalidUserDataException e) {
            e.printStackTrace();
        }
    }
}
