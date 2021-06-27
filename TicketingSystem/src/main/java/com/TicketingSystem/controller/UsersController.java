package com.TicketingSystem.controller;



import com.TicketingSystem.dto.mappers.UserMapper;
import com.TicketingSystem.dto.request.RegistrationRequest;
import com.TicketingSystem.model.User;
import com.TicketingSystem.service.UserService;


import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UsersController(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

//create user
    @PostMapping
    public void addUser(@Valid @RequestBody RegistrationRequest registrationRequest){
        User user = userMapper.toUser(registrationRequest);
        userService.addUser(user);
    }

//updateUser
    @PutMapping(value = "/{id}")
    public void updateUser(@Valid @RequestBody User user , @PathVariable long id ){
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
