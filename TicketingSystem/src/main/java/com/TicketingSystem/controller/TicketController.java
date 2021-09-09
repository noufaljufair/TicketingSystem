package com.TicketingSystem.controller;

import com.TicketingSystem.dto.mappers.TicketMapper;
import com.TicketingSystem.dto.request.AddTicketRequest;
import com.TicketingSystem.dto.response.AbstractTicketDto;
import com.TicketingSystem.dto.response.AllTicketDto;
import com.TicketingSystem.dto.response.DetailedTicketDto;
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
    private final TicketMapper ticketMapper;

    public TicketController(TicketService ticketService, TicketMapper ticketMapper){
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
    }

    @GetMapping("/admin")
    public List<AllTicketDto> getAllTickets(@RequestParam(required = false) Direction direction){
        List<Ticket> tickets = ticketService.getAllTickets(direction == null? getDirection() : direction);
        return ticketMapper.toAllTicketDtoList(tickets);
    }

    @GetMapping("admin/subject")
    public List<AllTicketDto> getAllTicketBySubject(@RequestParam String subject, @RequestParam(required = false) Direction direction){
        List<Ticket> tickets = ticketService.getAllTicketBySubject(subject, direction == null? getDirection() : direction);
        return ticketMapper.toAllTicketDtoList(tickets);
    }

    @GetMapping("admin/category")
    public List<AllTicketDto> getAllTicketByCategory(@RequestParam Category category, @RequestParam(required = false) Direction direction){
        List<Ticket> tickets = ticketService.getAllTicketByCategory(category, direction == null? getDirection() : direction);
        return ticketMapper.toAllTicketDtoList(tickets);
    }

    @GetMapping("admin/status")
    public List<AllTicketDto> getAllTicketByStatus(@RequestParam TicketStatus status, @RequestParam(required = false) Direction direction){
        List<Ticket> tickets = ticketService.getAllTicketByStatus(status, direction == null? getDirection() : direction);
        return ticketMapper.toAllTicketDtoList(tickets);
    }

    @PutMapping(value = "admin/{id}")
    public void updateTicketStatus(@Valid @RequestBody TicketStatus ticketStatus, @PathVariable long id ){
        ticketService.updateTicketStatus(ticketStatus, id);
    }




    @PostMapping("/client")
    public void addTicket(@Valid @RequestBody AddTicketRequest addTicketRequest){
        Ticket ticket = ticketMapper.toTicket(addTicketRequest);
        ticketService.addTicket(ticket);
    }

    @DeleteMapping(value = "client/{id}")
    public void deleteTicket(@PathVariable long id ){
        ticketService.deleteTicket(id);
    }


    @GetMapping("client/{id}/subject")
    public List<AbstractTicketDto> getUserTicketBySubject(@RequestParam String subject, @PathVariable long id, @RequestParam(required = false) Direction direction){
        List<Ticket> tickets = ticketService.getUserTicketBySubject(subject, id, direction == null? getDirection() : direction);
        return ticketMapper.toAbstractTicketDtoList(tickets);
    }

    @GetMapping("client/{id}/category")
    public List<AbstractTicketDto> getUserTicketByCategory(@RequestParam Category category, @PathVariable long id, @RequestParam(required = false) Direction direction){
        List<Ticket> tickets = ticketService.getUserTicketByCategory(category, id, direction == null? getDirection() : direction);
        return ticketMapper.toAbstractTicketDtoList(tickets);
    }

    @GetMapping("client/{id}/status")
    public List<AbstractTicketDto> getUserTicketByStatus(@RequestParam TicketStatus status, @PathVariable long id, @RequestParam(required = false) Direction direction){
        List<Ticket> tickets = ticketService.getUserTicketByStatus(status, id, direction == null? getDirection() : direction);
        return ticketMapper.toAbstractTicketDtoList(tickets);
    }



    @GetMapping("/client/{id}")
    public List<AbstractTicketDto> getTicketByUserId(@PathVariable long id, @RequestParam(required = false) Direction direction){
        List<Ticket> tickets = ticketService.getTicketByUserId(id, direction == null? getDirection() : direction);
        return ticketMapper.toAbstractTicketDtoList(tickets);
    }

    @GetMapping("/{id}")
    public DetailedTicketDto getTicketById(@PathVariable long id){
        Ticket ticket = ticketService.getTicketById(id);
        return ticketMapper.toDetailedTicketDto(ticket);
    }

    private Direction getDirection(){
        return Direction.ASC;
    }
}
