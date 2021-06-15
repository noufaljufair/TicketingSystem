package com.example.TicketingSystem.service;

import com.example.TicketingSystem.repository.UserRepository;
import com.example.TicketingSystem.model.Ticket;
import com.example.TicketingSystem.model.User;
import com.example.TicketingSystem.model.enums.UserType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


   public void addUser(User user){
      userRepository.save(user);
   }

   public void updateUser(User user){
      userRepository.save(user);
   }


   public void deleteUser(long id){
      userRepository.deleteById(id);
   }

}
