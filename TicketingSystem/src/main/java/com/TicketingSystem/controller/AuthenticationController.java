package com.TicketingSystem.controller;

import com.TicketingSystem.dto.mappers.UserMapper;
import com.TicketingSystem.dto.request.RegistrationRequest;
import com.TicketingSystem.model.User;
import com.TicketingSystem.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {
    private final UserService userService;
    private final UserMapper userMapper;

    public AuthenticationController(UserService userService,UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody RegistrationRequest registrationRequest){
        User user = userMapper.toUser(registrationRequest);
        userService.addUser(user);
    }
}
