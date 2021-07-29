package com.TicketingSystem.service;

import com.TicketingSystem.model.Ticket;
import com.TicketingSystem.model.enums.Category;
import com.TicketingSystem.model.enums.TicketStatus;
import com.TicketingSystem.repository.TicketRepository;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }


    public List<Ticket> getAllTickets(Sort.Direction direction){
        return ticketRepository.findAll(Sort.by(direction,"LastModifiedDate"));
    }

    public List<Ticket> getAllTicketBySubject(String subject, Sort.Direction direction){
        return ticketRepository.findBySubject(subject, Sort.by(direction,"LastModifiedDate"));
    }

    public List<Ticket> getAllTicketByCategory(Category category, Sort.Direction direction){
        return ticketRepository.findByCategory(category, Sort.by(direction,"LastModifiedDate"));
    }

    public List<Ticket> getAllTicketByStatus(TicketStatus status, Sort.Direction direction){
        return ticketRepository.findByStatus(status, Sort.by(direction,"LastModifiedDate"));
    }

    @PreAuthorize("hasRole('ADMIN') or principal.getId() == #id")
    public List<Ticket> getTicketByUserId(long id, Sort.Direction direction){
        return ticketRepository.findByUserId(id, Sort.by(direction,"LastModifiedDate"));
    }

    @PostAuthorize("hasRole('ADMIN') or principal.getId() == returnObject.getUser().getId()")
    public Ticket getTicketById(long id){
        return ticketRepository.findById(id).get();
    }

    public void updateTicketStatus(TicketStatus ticketStatus, long id){
        ticketRepository.updateTicketStatus(ticketStatus, id);
    }

    public void addTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }
    
    @PreAuthorize("@authorizationInspector.inspectTicket(#ticketId, principal.getId())")
    public void deleteTicket(long ticketId){
        ticketRepository.deleteById(ticketId);
    }

    @PreAuthorize("principal.getId() == #id")
    public List<Ticket> getUserTicketBySubject(String subject, long id, Sort.Direction direction) {
        return ticketRepository.findBySubjectAndUserId(subject, id, Sort.by(direction,"LastModifiedDate"));
    }

    @PreAuthorize("principal.getId() == #id")
    public List<Ticket> getUserTicketByCategory(Category category, long id, Sort.Direction direction) {
        return ticketRepository.findByCategoryAndUserId(category, id, Sort.by(direction,"LastModifiedDate"));
    }

    @PreAuthorize("principal.getId() == #id")
    public List<Ticket> getUserTicketByStatus(TicketStatus status, long id, Sort.Direction direction) {
        return ticketRepository.findByStatusAndUserId(status, id, Sort.by(direction,"LastModifiedDate"));
    }

}
