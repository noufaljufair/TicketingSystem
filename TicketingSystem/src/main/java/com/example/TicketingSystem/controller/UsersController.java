package com.example.TicketingSystem.controller;


import com.example.TicketingSystem.model.Ticket;
import com.example.TicketingSystem.model.User;
import com.example.TicketingSystem.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import org.springframework.security.authentication.AuthenticationManager;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService){
        this.userService = userService;
    }

//create user
    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

//updateUser
    @PutMapping(value = "/{id}")
    public void updateUser(@RequestBody User user , @PathVariable long id ){
        userService.updateUser(user);
    }

//delete user
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable long id ){ userService.deleteUser(id); }

//get all users
    @GetMapping
    public List<User> getAllUsers(){ return userService.getAllUsers();}


//get user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id){ return userService.getUserById(id); }

}
