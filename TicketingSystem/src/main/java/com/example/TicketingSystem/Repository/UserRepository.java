package com.example.TicketingSystem.Repository;

import com.example.TicketingSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User ,Long> {
}
