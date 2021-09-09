package com.TicketingSystem.service;

import com.TicketingSystem.configuration.Translator;
import com.TicketingSystem.dto.request.ChangePasswordDto;
import com.TicketingSystem.exception.ResourceNotFoundException;
import com.TicketingSystem.model.User;
import com.TicketingSystem.model.enums.Gender;
import com.TicketingSystem.repository.UserRepository;
import com.TicketingSystem.security.JwtUtil;
import com.TicketingSystem.security.RedisUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public void updateUser(String firstName, String lastName, Gender gender, int avatar,long  id){
        userRepository.updateUser(firstName, lastName, gender, avatar, id);
   }
    @PreAuthorize("hasRole('ADMIN') or principal.getId() == #id")
    public User getUserById(long id){
      return userRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException(Translator.toLocale("error.user.id.notFound")));
 }

    @PreAuthorize("principal.getId() == #id")
    public void deleteUser(long id, String token){
        redisUtils.save(token, jwtUtil.extractExpirationDate(token));
        userRepository.deleteById(id);
   }

   @PreAuthorize("principal.getId() == #userId")
   @Transactional
    public boolean changePassword(ChangePasswordDto changePasswordDto, long userId) {
       User user = userRepository.findById(userId).get();
       if (new BCryptPasswordEncoder().matches(changePasswordDto.getCurrentPassword(), user.getPassword())) {
           userRepository.changePassword(userId, new BCryptPasswordEncoder().encode(changePasswordDto.getPassword()));
           return true;
       }
       return false;
    }
}
