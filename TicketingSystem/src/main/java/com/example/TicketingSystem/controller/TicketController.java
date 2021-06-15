package com.example.TicketingSystem.controller;




import com.example.TicketingSystem.model.enums.Category;
import com.example.TicketingSystem.model.enums.TicketStatus;
import com.example.TicketingSystem.service.TicketService;
import org.springframework.web.bind.annotation.*;
import com.example.TicketingSystem.model.Ticket;

import javax.validation.Valid;
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
    public void addTicket(@Valid @RequestBody Ticket ticket){
         ticketService.addTicket(ticket);
    }


    //update ticket
    @PutMapping(value = "/{id}")
    public void updateTicket(@Valid @RequestBody Ticket ticket , @PathVariable long id ){
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



    @GetMapping("/status")
    public List<Ticket> getTicketByStatus(@RequestParam TicketStatus status){
        return ticketService.getTicketByStatus(status);
    }

    @GetMapping("/user/{id}")
    public List<Ticket> getTicketByUserId(@PathVariable long id){
        return ticketService.getTicketByUserId(id);
    }
}
