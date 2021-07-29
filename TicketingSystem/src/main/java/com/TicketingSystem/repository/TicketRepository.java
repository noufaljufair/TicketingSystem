package com.TicketingSystem.repository;

import com.TicketingSystem.model.Ticket;
import com.TicketingSystem.model.enums.Category;
import com.TicketingSystem.model.enums.TicketStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCategory(Category category, Sort sort);

    List<Ticket> findByStatus(TicketStatus status, Sort sort);

    List<Ticket> findByUserId(Long id, Sort sort);

    List<Ticket> findBySubject(String subject, Sort sort);

    List<Ticket> findByCategoryAndUserId(Category category, long userId, Sort sort);

    List<Ticket> findByStatusAndUserId(TicketStatus status, long userId, Sort sort);

    List<Ticket> findBySubjectAndUserId(String subject, long userId, Sort sort);

    @Transactional
    @Modifying
    @Query("update Ticket ticket set ticket.status = ?1 where ticket.id = ?2")
    void updateTicketStatus(TicketStatus ticketStatus, Long id);
}
