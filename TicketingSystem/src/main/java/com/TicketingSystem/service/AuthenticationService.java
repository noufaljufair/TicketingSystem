package com.TicketingSystem.service;

import com.TicketingSystem.configuration.Translator;
import com.TicketingSystem.repository.UserRepository;


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


@Service
public class AuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationService(UserRepository  userRepository){
        this.userRepository = userRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void registerUser(User user){
        if(doesEmailExists(user.getEmail()))
            throw new DataIntegrityViolationException("", new Throwable("duplicate email"));

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void authenticate(String email, String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(Translator.toLocale("AbstractUserDetailsAuthenticationProvider.badCredentials")));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), user.getRole().getGrantedAuthorities());
    }

    public boolean doesEmailExists(String email){
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
}
