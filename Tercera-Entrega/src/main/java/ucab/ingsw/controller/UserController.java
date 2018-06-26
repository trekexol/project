package ucab.ingsw.controller;

import ucab.ingsw.command.UserChangingAttributesCommand;
import ucab.ingsw.command.*;
import ucab.ingsw.model.User;
import ucab.ingsw.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

import ucab.ingsw.command.UserLoginCommand;

@Slf4j

@CrossOrigin
@RestController
@RequestMapping(value = "/user", produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/register", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity register(@Valid @RequestBody UserSignUpCommand command) {
        return userService.register(command);
    }

    @RequestMapping(value = "/login", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity login(@Valid @RequestBody UserLoginCommand command) {
        return userService.login(command);
    }

    @RequestMapping(value = "/update/{id}", consumes = "application/json", method = RequestMethod.PUT)
    public ResponseEntity update(@Valid @RequestBody UserChangingAttributesCommand command, @PathVariable("id") String id) {
        return userService.update(command, id);
    }

    @RequestMapping(value = "/delete/{id}", consumes = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id) {
        return userService.delete(id);
    }

   @RequestMapping(value = "/search/{firstName}", consumes = "application/json", method = RequestMethod.GET)
     public List<User> searchByName(@PathVariable("firstName") String firstName) {
        return userService.searchByName(firstName);
    }

    @RequestMapping(value = "/add/friend", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Object> addFriend(@Valid @RequestBody AddFriendCommand command) {
        return userService.addFriend(command);
    }


}


