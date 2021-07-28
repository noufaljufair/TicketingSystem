package com.TicketingSystem.service;

import com.TicketingSystem.model.User;
import com.TicketingSystem.repository.UserRepository;
import com.TicketingSystem.security.JwtUtil;
import com.TicketingSystem.security.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final RedisUtils redisUtils;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil, RedisUtils redisUtils){
      this.userRepository = userRepository;
      this.jwtUtil = jwtUtil;
      this.redisUtils = redisUtils;
   }

    @PreAuthorize("principal.getId() == #id")
    public void updateUser(User user, long id){
        userRepository.save(user);
   }
    @PreAuthorize("hasRole('ADMIN') or principal.getId() == #id")
    public User getUserById(long id){
      return userRepository.findById(id).get();
 }

    @PreAuthorize("principal.getId() == #id")
    public void deleteUser(long id, String token){
        redisUtils.save(token, jwtUtil.extractExpirationDate(token));
        userRepository.deleteById(id);
   }
}
