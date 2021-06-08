package com.example.TicketingSystem.Repository;

import com.example.TicketingSystem.model.Ticket;
import com.example.TicketingSystem.model.enums.Category;
import com.example.TicketingSystem.model.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCategory(Category category);

    List<Ticket> findByStatus(TicketStatus status);

    List<Ticket> findByUserId(Long id);


}
