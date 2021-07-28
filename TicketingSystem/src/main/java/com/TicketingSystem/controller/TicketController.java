package com.TicketingSystem.controller;

import com.TicketingSystem.model.Ticket;
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

    @GetMapping("/admin")//admin✅
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("admin/subject")//admin✅
    public List<Ticket> getTicketBySubject(@RequestParam String subject){
        return ticketService.getTicketBySubject(subject);
    }

    @PutMapping(value = "admin/{id}")//admin, status only✅
    public void updateTicketStatus(@Valid @RequestBody TicketStatus ticketStatus, @PathVariable long id ){
        ticketService.updateTicketStatus(ticketStatus, id);
    }

    @GetMapping("/{id}")//admin or owner✅
    public Ticket getTicketById(@PathVariable long id){
        return ticketService.getTicketById(id);
    }

    //✅
    @PostMapping("/client")//client
    public void addTicket(@Valid @RequestBody Ticket ticket){
         ticketService.addTicket(ticket);
    }

    //✅
    @DeleteMapping(value = "client/{id}")//owner✅
    public void deleteTicket(@PathVariable long id ){
        ticketService.deleteTicket(id);
    }

    //✅
    @GetMapping("/user/{id}")//admin or owner
    public List<Ticket> getTicketByUserId(@PathVariable long id){
        return ticketService.getTicketByUserId(id);
    }

//    @GetMapping("/category")
//    public List<Ticket> getTicketByCategory(@RequestParam Category category){
//        return ticketService.getTicketByCategory(category);
//    }
//
//    @GetMapping("/status")
//    public List<Ticket> getTicketByStatus(@RequestParam TicketStatus status){
//        return ticketService.getTicketByStatus(status);
//    }

//     @GetMapping("/sort_asc")
//    public List<Ticket> sortByLastModifiedDateAsc(){
//        return ticketService.sortByLastModifiedDateAsc();
//    }
//
//    @GetMapping("/sort_des")
//    public List<Ticket> sortByLastModifiedDateDes(){
//        return ticketService.sortByLastModifiedDateDes();
//    }
}
