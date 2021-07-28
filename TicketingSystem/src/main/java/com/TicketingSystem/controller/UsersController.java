package com.TicketingSystem.controller;

import com.TicketingSystem.model.Principal;
import com.TicketingSystem.model.User;
import com.TicketingSystem.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService){
        this.userService = userService;
    }

    @PutMapping(value = "/{id}") //owner✅
    public void updateUser(@Valid @RequestBody User user , @PathVariable long id ){
        userService.updateUser(user, id);
    }

    @DeleteMapping(value = "/{id}")//user✅
    public void deleteUser(@PathVariable long id, HttpServletRequest request ) throws ServletException {
        final String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            userService.deleteUser(id, bearerToken.substring(7));
            request.logout();
        }
    }

    @GetMapping("/{id}")//admin or that user✅
    public User getUserById(@PathVariable long id){ return userService.getUserById(id); }

}
