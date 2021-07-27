package com.TicketingSystem.service;

import com.TicketingSystem.configuration.Translator;
import com.TicketingSystem.model.Principal;
import com.TicketingSystem.repository.UserRepository;


import com.TicketingSystem.security.JwtUtil;
import com.TicketingSystem.security.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.TicketingSystem.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service
public class AuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RedisUtils redisUtils;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationService(UserRepository userRepository, RedisUtils redisUtils){
        this.userRepository = userRepository;
        this.redisUtils = redisUtils;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void registerUser(User user){
        if(doesEmailExists(user.getEmail()))
            throw new DataIntegrityViolationException("", new Throwable("duplicate email"));

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String authenticate(String email, String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        final UserDetails user = (UserDetails) authentication.getPrincipal();

        return jwtUtil.generateToken(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(Translator.toLocale("AbstractUserDetailsAuthenticationProvider.badCredentials")));
        return new Principal(user.getId(), user.getEmail(), user.getPassword(), user.getRole().getGrantedAuthorities());
    }

    public void logout(String token){
        redisUtils.save(token, jwtUtil.extractExpirationDate(token));
    }

    public String generateRefreshToken(Map<String, Object> claims, String subject) {
        return jwtUtil.doGenerateToken(claims, subject, true);
    }

    private boolean doesEmailExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

}
