package com.TicketingSystem.controller;

import com.TicketingSystem.model.Ticket;
import com.TicketingSystem.model.enums.Category;
import com.TicketingSystem.model.enums.TicketStatus;
import com.TicketingSystem.service.TicketService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("/sort_asc")
    public List<Ticket> sortByLastModifiedDateAsc(){
        return ticketService.sortByLastModifiedDateAsc();
    }

    @GetMapping("/sort_des")
    public List<Ticket> sortByLastModifiedDateDes(){
        return ticketService.sortByLastModifiedDateDes();
    }

    @GetMapping("/subject")
    public Ticket getTicketBySubject(@RequestParam String subject){
        return ticketService.getTicketBySubject(subject);
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable long id){
        return ticketService.getTicketById(id);
    }

    @PostMapping
    public void addTicket(@Valid @RequestBody Ticket ticket){
         ticketService.addTicket(ticket);
    }

    @PutMapping(value = "/{id}")
    public void updateTicket(@Valid @RequestBody Ticket ticket , @PathVariable long id ){
        ticketService.updateTicket(ticket);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTicket(@PathVariable long id ){
        ticketService.deleteTicket(id);
    }

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
