package com.TicketingSystem.repository;

import com.TicketingSystem.model.User;
import com.TicketingSystem.model.enums.UserType;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    List<User> findByType(UserType type);



}
