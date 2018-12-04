package edu.utcluj.track.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Maria on 11.11.2018.
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/create", headers = "Accept=application/json")
    public User save(@RequestBody User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("Invalid 'id' value. It should be empty");
        }

        if(userService.getUser(user.getUserName(), user.getPassword()) == null){
            throw new IllegalArgumentException("Invalid username value. This username already exists!");
        }
        return userService.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{userName}/{password}")
    public boolean delete(@PathVariable("userName") String userName, @PathVariable("password") String password){
        if(userService.deleteUser(userName, password)){
            return true;
        }
        else {
            throw new IllegalArgumentException("Invalid user! This user doesn't exists in the DataBase.");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updatePassword{userName}/{password}")
    public User update(@PathVariable("userName") String userName, @PathVariable("password") String newPassword)throws IllegalArgumentException{
        return userService.updatePassword(userName, newPassword);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/read/{userName}/{password}")
    public User read (@PathVariable("userName") String userName, @PathVariable("password") String password)throws IllegalArgumentException{
        return userService.getUser(userName, password);
    }

}
