package com.example.TicketingSystem.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.TicketingSystem.Repository.UserRepository;
import com.example.TicketingSystem.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {


   @Autowired
   private UserRepository userRepository;



   public List<User> getAllUsers(){
      List<User> users = new ArrayList<>();
      userRepository.findAll().forEach(users::add);
      return users;
   }


   public User getUserById(long id){
      return userRepository.findById(id).get();
 }

   public List<User> getUserByType(int type){

      List<User> users = new ArrayList<>();
      userRepository.findAll().stream().filter(u -> u.getType() == type).forEach(users::add);
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
