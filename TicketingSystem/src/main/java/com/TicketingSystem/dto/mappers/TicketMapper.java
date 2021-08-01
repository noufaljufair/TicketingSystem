package com.TicketingSystem.dto.mappers;

import com.TicketingSystem.dto.response.AbstractTicketDto;
import com.TicketingSystem.dto.response.AllTicketDto;
import com.TicketingSystem.dto.response.DetailedTicketDto;
import com.TicketingSystem.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface TicketMapper {

    @Mapping(source = "user.firstName", target = "clientFirstName")
    @Mapping(source = "user.lastName", target = "clientLastName")
    AllTicketDto toAllTicketDto(Ticket ticket);

    List<AllTicketDto> toAllTicketDtoList(List<Ticket> tickets);

    AbstractTicketDto toAbstractTicketDto(Ticket ticket);

    List<AbstractTicketDto> toAbstractTicketDtoList(List<Ticket> tickets);

    @Mapping(source = "user.firstName", target = "clientFirstName")
    @Mapping(source = "user.lastName", target = "clientLastName")
    DetailedTicketDto toDetailedTicketDto(Ticket ticket);
}
