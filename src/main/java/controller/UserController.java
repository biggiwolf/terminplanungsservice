package controller;

import models.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import service.EventService;
import service.UserService;
import service.exceptions.InvalidUserDataException;
import service.exceptions.NoSuchUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/greeting")
    public String index(){
        LOGGER.debug("/ MAPPING");
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/all")
    public List<User> allUsers(){
        LOGGER.debug("/users mapping");
        return UserService.getInstance().showAllUsers();
    }

    @GetMapping("/{id}")
    public User showOneUser(@PathVariable long id){
        LOGGER.debug("/users/{id} GET mapping");
        try {
            return UserService.getInstance().showOneUser(id);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/{id}")
    public void addOrReplaceUser(@RequestBody User user, @PathVariable long id){
        LOGGER.debug("/users PUT mapping");
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

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id){
        LOGGER.debug("/users DELETE mapping");
        try {
            UserService.getInstance().deleteUser(id);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        } catch (InvalidUserDataException e) {
            e.printStackTrace();
        }
    }
}
