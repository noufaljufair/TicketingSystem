package com.TicketingSystem.controller;

import com.TicketingSystem.model.Ticket;
import com.TicketingSystem.model.enums.Category;
import com.TicketingSystem.model.enums.TicketStatus;
import com.TicketingSystem.service.TicketService;
import org.springframework.data.domain.Sort.Direction;
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

    @GetMapping("/admin")
    public List<Ticket> getAllTickets(@RequestParam(required = false) Direction direction){
        return ticketService.getAllTickets(direction == null? getDirection() : direction);
    }

    @GetMapping("admin/subject")
    public List<Ticket> getAllTicketBySubject(@RequestParam String subject, @RequestParam(required = false) Direction direction){
        return ticketService.getAllTicketBySubject(subject, direction == null? getDirection() : direction);
    }

    @GetMapping("admin/category")
    public List<Ticket> getAllTicketByCategory(@RequestParam Category category, @RequestParam(required = false) Direction direction){
        return ticketService.getAllTicketByCategory(category, direction == null? getDirection() : direction);
    }

    @GetMapping("admin/status")
    public List<Ticket> getAllTicketByStatus(@RequestParam TicketStatus status, @RequestParam(required = false) Direction direction){
        return ticketService.getAllTicketByStatus(status, direction == null? getDirection() : direction);
    }

    @PutMapping(value = "admin/{id}")
    public void updateTicketStatus(@Valid @RequestBody TicketStatus ticketStatus, @PathVariable long id ){
        ticketService.updateTicketStatus(ticketStatus, id);
    }




    @PostMapping("/client")
    public void addTicket(@Valid @RequestBody Ticket ticket){
        ticketService.addTicket(ticket);
    }

    @DeleteMapping(value = "client/{id}")
    public void deleteTicket(@PathVariable long id ){
        ticketService.deleteTicket(id);
    }


    @GetMapping("client/{id}/subject")
    public List<Ticket> getUserTicketBySubject(@RequestParam String subject, @PathVariable long id, @RequestParam(required = false) Direction direction){
        return ticketService.getUserTicketBySubject(subject, id, direction == null? getDirection() : direction);
    }

    @GetMapping("client/{id}/category")
    public List<Ticket> getUserTicketByCategory(@RequestParam Category category, @PathVariable long id, @RequestParam(required = false) Direction direction){
        return ticketService.getUserTicketByCategory(category, id, direction == null? getDirection() : direction);
    }

    @GetMapping("client/{id}/status")
    public List<Ticket> getUserTicketByStatus(@RequestParam TicketStatus status, @PathVariable long id, @RequestParam(required = false) Direction direction){
        return ticketService.getUserTicketByStatus(status, id, direction == null? getDirection() : direction);
    }



    @GetMapping("/user/{id}")
    public List<Ticket> getTicketByUserId(@PathVariable long id, @RequestParam(required = false) Direction direction){
        return ticketService.getTicketByUserId(id, direction == null? getDirection() : direction);
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable long id){
        return ticketService.getTicketById(id);
    }



    private Direction getDirection(){
        return Direction.ASC;
    }
}
