package com.TicketingSystem.service;

import com.TicketingSystem.model.Ticket;
import com.TicketingSystem.repository.TicketRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationInspector {
    private final TicketRepository ticketRepository;

    public AuthorizationInspector(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }
    public boolean inspectTicket(long ticketId, long userId){
       Ticket ticket = ticketRepository.findById(ticketId).get();
       return ticket.getUser().getId() == userId;
    }
}
