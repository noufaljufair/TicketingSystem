package com.TicketingSystem.controller;

import com.TicketingSystem.dto.mappers.UserMapper;
import com.TicketingSystem.dto.request.AuthenticationRequest;
import com.TicketingSystem.dto.request.RegistrationRequest;
import com.TicketingSystem.model.User;
import com.TicketingSystem.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {
    private AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public AuthenticationController(UserMapper userMapper, AuthenticationService authenticationService){
        this.userMapper = userMapper;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody RegistrationRequest registrationRequest){
        User user = userMapper.toUser(registrationRequest);
        authenticationService.registerUser(user);
    }

    @PostMapping("/login")
    public void login(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        authenticationService.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
    }
}
