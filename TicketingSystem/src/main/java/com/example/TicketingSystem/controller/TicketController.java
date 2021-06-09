package com.example.TicketingSystem.controller;



import com.example.TicketingSystem.model.Ticket;
import com.example.TicketingSystem.model.enums.Category;
import com.example.TicketingSystem.model.enums.TicketStatus;
import com.example.TicketingSystem.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    //get all tickets
    @RequestMapping("/tickets")
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }


    //get ticket by id
    @RequestMapping("/tickets/{id}")
    public Ticket getTicketById(@PathVariable long id){
        return ticketService.getTicketById(id);
    }



    //create new ticket
    @RequestMapping(method=RequestMethod.POST, value = "/tickets")
    public void addTicket(@RequestBody Ticket ticket){
         ticketService.addTicket(ticket);
    }


    //update ticket
    @RequestMapping(method=RequestMethod.PUT, value = "/tickets/{id}")
    public void addTopic(@RequestBody Ticket ticket , @PathVariable long id ){
        ticketService.updateTicket(ticket);
    }



    //delete ticket
    @RequestMapping(method=RequestMethod.DELETE, value = "/tickets/{id}")
    public void deleteTopic(@PathVariable long id ){
        ticketService.deleteTicket(id);
    }



    //get ticket by category
    @RequestMapping("/tickets/category")
    public List<Ticket> getTicketByCategory(@PathVariable Category category){
        return ticketService.getTicketByCategory(category);
    }


    //get ticker by status
    @RequestMapping("/tickets/status")
    public List<Ticket> getTicketByStatus(@PathVariable TicketStatus status){
        return ticketService.getTicketByStatus(status);
    }

}
