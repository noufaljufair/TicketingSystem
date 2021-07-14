package com.TicketingSystem.controller;

import com.TicketingSystem.dto.mappers.UserMapper;
import com.TicketingSystem.dto.request.AuthenticationRequest;
import com.TicketingSystem.dto.request.RegistrationRequest;
import com.TicketingSystem.dto.response.AuthenticationResponse;
import com.TicketingSystem.model.User;
import com.TicketingSystem.service.AuthenticationService;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public AuthenticationController(UserMapper userMapper, AuthenticationService authenticationService){
        this.userMapper = userMapper;
        this.authenticationService = authenticationService;
    }

    @PostMapping("register")
    public void registerUser(@Valid @RequestBody RegistrationRequest registrationRequest){
        User user = userMapper.toUser(registrationRequest);
        authenticationService.registerUser(user);
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        String token = authenticationService.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request) throws ServletException {
        final String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            authenticationService.logout(bearerToken.substring(7));
            request.logout();
        }
    }

    @GetMapping("refreshToken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) throws Exception {
        DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");
        if (claims == null) {
            return ResponseEntity.status(403).build();
        }
        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String token = authenticationService.generateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    private Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }

}
