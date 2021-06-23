package com.TicketingSystem.service;

import com.TicketingSystem.model.User;
import com.TicketingSystem.repository.UserRepository;
import com.TicketingSystem.model.enums.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class UserService {

   private final UserRepository userRepository;

   public UserService(UserRepository userRepository){
      this.userRepository = userRepository;
   }


   public List<User> getAllUsers(){
      List<User> users = new ArrayList<>();
      userRepository.findAll().forEach(users::add);
      return users;
   }


   public User getUserById(long id){
      return userRepository.findById(id).get();
 }

   public List<User> getUserByType(UserType type){
      List<User> users = new ArrayList<>();
      userRepository.findByType(type).forEach(users::add);
      return users;
}

   @Transactional(isolation = Isolation.READ_COMMITTED)
   public void addUser(User user){
      if(doesEmailExists(user.getEmail()))
         throw new DataIntegrityViolationException("", new Throwable("duplicate email"));

      userRepository.save(user);
   }

   public void updateUser(User user){
      userRepository.save(user);
   }


   public void deleteUser(long id){
      userRepository.deleteById(id);
   }

   public boolean doesEmailExists(String email){
      return userRepository.findByEmail(email).isPresent();
   }

}
