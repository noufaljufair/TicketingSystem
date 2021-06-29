package com.TicketingSystem.repository;

import com.TicketingSystem.model.User;
import com.TicketingSystem.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    List<User> findByRole(UserRole role);



}
