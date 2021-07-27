package com.TicketingSystem.repository;

import com.TicketingSystem.model.Ticket;
import com.TicketingSystem.model.enums.Category;
import com.TicketingSystem.model.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCategory(Category category);

    List<Ticket> findByStatus(TicketStatus status);

    List<Ticket> findByUserId(Long id);

    List<Ticket> findBySubject(String subject);

    @Transactional
    @Modifying
    @Query("update Ticket ticket set ticket.status = ?1 where ticket.id = ?2")
    void updateTicketStatus(TicketStatus ticketStatus, Long id);
}
