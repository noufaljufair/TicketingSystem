package com.example.TicketingSystem.controller;


import com.example.TicketingSystem.model.Ticket;
import com.example.TicketingSystem.model.User;
import com.example.TicketingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class UsersController {


    @Autowired
    private UserService userService;

//create user
    @RequestMapping(method=RequestMethod.POST, value = "/users")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

//updateUser
    @RequestMapping(method=RequestMethod.POST, value = "/users/{id}")
    public void addTopic(@RequestBody User user , @PathVariable long id ){
        userService.updateUser(user);
    }

//delete user
    @RequestMapping(method=RequestMethod.POST, value = "/users/{id}")
    public void deleteTopic(@PathVariable long id ){ userService.deleteUser(id); }



//get all users
    @RequestMapping(value = "/getAllUsers")
    public List<User> getAllUsers(){ return userService.getAllUsers();}


//get user by ID
    @RequestMapping("/users/{id}")

    public User getUesrById(@PathVariable long id){ return userService.getUserById(id); }

}
