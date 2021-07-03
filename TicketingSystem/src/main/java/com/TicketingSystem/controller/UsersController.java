package com.TicketingSystem.controller;

import com.TicketingSystem.model.User;
import com.TicketingSystem.service.UserService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService){
        this.userService = userService;
    }

    @PutMapping(value = "/{id}")
    public void updateUser(@Valid @RequestBody User user , @PathVariable long id ){
        userService.updateUser(user);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable long id ){ userService.deleteUser(id); }

    @GetMapping
    public List<User> getAllUsers(){ return userService.getAllUsers();}

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id){ return userService.getUserById(id); }

}
