package com.example.TicketingSystem.controller;


import com.example.TicketingSystem.model.Ticket;
import com.example.TicketingSystem.model.User;
import com.example.TicketingSystem.service.UserService;
import com.example.TicketingSystem.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import org.springframework.security.authentication.AuthenticationManager;


@RestController
public class UsersController {


    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;

//create user
    @RequestMapping(method=RequestMethod.POST, value = "/users")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

//updateUser
    @RequestMapping(method=RequestMethod.PUT, value = "/users/{id}")
    public void addTopic(@RequestBody User user , @PathVariable long id ){
        userService.updateUser(user);
    }

//delete user
    @RequestMapping(method=RequestMethod.DELETE, value = "/users/{id}")
    public void deleteTopic(@PathVariable long id ){ userService.deleteUser(id); }


   //get Ticket by user ID
    @RequestMapping("/users/{id}/tickets")
    public List<Ticket> getTicketByUserId(@PathVariable long id){
        return ticketService.getTicketByUserId(id);
    }


//get all users
    @RequestMapping(value = "/getAllUsers")
    public List<User> getAllUsers(){ return userService.getAllUsers();}


//get user by ID
    @RequestMapping("/users/{id}")

    public User getUesrById(@PathVariable long id){ return userService.getUserById(id); }

}
