package com.TicketingSystem.controller;

import com.TicketingSystem.dto.mappers.UserMapper;
import com.TicketingSystem.dto.request.UpdateUserRequest;
import com.TicketingSystem.dto.response.UserDto;
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
    private final UserMapper userMapper;
    public UsersController(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PutMapping(value = "/{id}")
    public void updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest, @PathVariable long id ){
        userService.updateUser(updateUserRequest.getFirstName(), updateUserRequest.getLastName(), updateUserRequest.getGender(), id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable long id, HttpServletRequest request ) throws ServletException {
        final String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            userService.deleteUser(id, bearerToken.substring(7));
            request.logout();
        }
    }

    @GetMapping("/{id}")//admin or that user✅
    public UserDto getUserById(@PathVariable long id){
        User user = userService.getUserById(id);
        return userMapper.toUserDto(user);
    }

}
