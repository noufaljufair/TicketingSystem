package com.example.TicketingSystem.controller;



import com.example.TicketingSystem.model.Ticket;
import com.example.TicketingSystem.model.enums.Category;
import com.example.TicketingSystem.model.enums.TicketStatus;
import com.example.TicketingSystem.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    //get all tickets
    @GetMapping
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }


    //get ticket by id
    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable long id){
        return ticketService.getTicketById(id);
    }



    //create new ticket
    @PostMapping
    public void addTicket(@RequestBody Ticket ticket){
         ticketService.addTicket(ticket);
    }


    //update ticket
    @PutMapping(value = "/{id}")
    public void updateTicket(@RequestBody Ticket ticket , @PathVariable long id ){
        ticketService.updateTicket(ticket);
    }



    //delete ticket
    @DeleteMapping(value = "/{id}")
    public void deleteTicket(@PathVariable long id ){
        ticketService.deleteTicket(id);
    }



    //get ticket by category
    @GetMapping("/category")
    public List<Ticket> getTicketByCategory(@RequestParam Category category){
        return ticketService.getTicketByCategory(category);
    }


    //get ticker by status
    @GetMapping("/status")
    public List<Ticket> getTicketByStatus(@PathVariable TicketStatus status){
        return ticketService.getTicketByStatus(status);
    }

}
