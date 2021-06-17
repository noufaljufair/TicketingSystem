package com.TicketingSystem.service;

import com.TicketingSystem.model.Auditable;
import com.TicketingSystem.model.Ticket;
import com.TicketingSystem.model.enums.Category;
import com.TicketingSystem.model.enums.TicketStatus;
import com.TicketingSystem.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets(){
        List<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return tickets;
    }

    public Ticket getTicketById(long id){
        return ticketRepository.findById(id).get();
    }

    public List<Ticket> getTicketByCategory(Category category){
        List<Ticket> tickets = new ArrayList<>();
        ticketRepository.findByCategory(category).forEach(tickets::add);
        return tickets;
    }

    public List<Ticket> getTicketByStatus(TicketStatus status){
        List<Ticket> tickets = new ArrayList<>();
        ticketRepository.findByStatus(status).forEach(tickets::add);
        return tickets;
    }

    public List<Ticket> getTicketByUserId(long id){
        return ticketRepository.findByUserId(id);
    }

    public void addTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public void updateTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public void deleteTicket(long ticketId){
        ticketRepository.deleteById(ticketId);
    }


//not finalized yet
 public List<Ticket> sortByLastModifiedDateAsc(){
     List<Ticket> tickets = new ArrayList<>();
     ticketRepository.findAll().forEach(tickets::add);
     Collections.sort(tickets , Comparator.comparing(Auditable::getLastModifiedDate));
     return tickets;

 }

    public List<Ticket> sortByLastModifiedDateDes(){
        List<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        Collections.sort(tickets , Comparator.comparing(Auditable::getLastModifiedDate));
        Collections.reverse(tickets);
        return tickets;

    }


    public Ticket getTicketBySubject(String subject){

        return ticketRepository.findBySubject(subject);

    }



}
