package com.TicketingSystem.dto.mappers;

import com.TicketingSystem.dto.request.AddTicketRequest;
import com.TicketingSystem.dto.response.AbstractTicketDto;
import com.TicketingSystem.dto.response.AllTicketDto;
import com.TicketingSystem.dto.response.DetailedTicketDto;
import com.TicketingSystem.model.Ticket;
import com.TicketingSystem.model.User;
import com.TicketingSystem.model.enums.Category;
import com.TicketingSystem.model.enums.TicketStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-22T11:04:39+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class TicketMapperImpl implements TicketMapper {

    @Override
    public AllTicketDto toAllTicketDto(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        String clientFirstName = null;
        String clientLastName = null;
        Long id = null;
        String subject = null;
        Category category = null;
        TicketStatus status = null;
        LocalDateTime createdDate = null;

        clientFirstName = ticketUserFirstName( ticket );
        clientLastName = ticketUserLastName( ticket );
        id = ticket.getId();
        subject = ticket.getSubject();
        category = ticket.getCategory();
        status = ticket.getStatus();
        createdDate = ticket.getCreatedDate();

        AllTicketDto allTicketDto = new AllTicketDto( id, subject, category, status, createdDate, clientFirstName, clientLastName );

        return allTicketDto;
    }

    @Override
    public List<AllTicketDto> toAllTicketDtoList(List<Ticket> tickets) {
        if ( tickets == null ) {
            return null;
        }

        List<AllTicketDto> list = new ArrayList<AllTicketDto>( tickets.size() );
        for ( Ticket ticket : tickets ) {
            list.add( toAllTicketDto( ticket ) );
        }

        return list;
    }

    @Override
    public AbstractTicketDto toAbstractTicketDto(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        Long id = null;
        String subject = null;
        TicketStatus status = null;

        id = ticket.getId();
        subject = ticket.getSubject();
        status = ticket.getStatus();

        AbstractTicketDto abstractTicketDto = new AbstractTicketDto( id, subject, status );

        return abstractTicketDto;
    }

    @Override
    public List<AbstractTicketDto> toAbstractTicketDtoList(List<Ticket> tickets) {
        if ( tickets == null ) {
            return null;
        }

        List<AbstractTicketDto> list = new ArrayList<AbstractTicketDto>( tickets.size() );
        for ( Ticket ticket : tickets ) {
            list.add( toAbstractTicketDto( ticket ) );
        }

        return list;
    }

    @Override
    public DetailedTicketDto toDetailedTicketDto(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        String clientFirstName = null;
        String clientLastName = null;
        String subject = null;
        String description = null;
        Category category = null;
        TicketStatus status = null;
        LocalDateTime createdDate = null;
        LocalDateTime lastModifiedDate = null;

        clientFirstName = ticketUserFirstName( ticket );
        clientLastName = ticketUserLastName( ticket );
        subject = ticket.getSubject();
        description = ticket.getDescription();
        category = ticket.getCategory();
        status = ticket.getStatus();
        createdDate = ticket.getCreatedDate();
        lastModifiedDate = ticket.getLastModifiedDate();

        DetailedTicketDto detailedTicketDto = new DetailedTicketDto( subject, description, category, status, createdDate, lastModifiedDate, clientFirstName, clientLastName );

        return detailedTicketDto;
    }

    @Override
    public Ticket toTicket(AddTicketRequest addTicketRequest) {
        if ( addTicketRequest == null ) {
            return null;
        }

        Ticket ticket = new Ticket();

        ticket.setUser( addTicketRequestToUser( addTicketRequest ) );
        ticket.setSubject( addTicketRequest.getSubject() );
        ticket.setDescription( addTicketRequest.getDescription() );
        ticket.setStatus( addTicketRequest.getStatus() );
        ticket.setCategory( addTicketRequest.getCategory() );

        return ticket;
    }

    private String ticketUserFirstName(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }
        User user = ticket.getUser();
        if ( user == null ) {
            return null;
        }
        String firstName = user.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String ticketUserLastName(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }
        User user = ticket.getUser();
        if ( user == null ) {
            return null;
        }
        String lastName = user.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    protected User addTicketRequestToUser(AddTicketRequest addTicketRequest) {
        if ( addTicketRequest == null ) {
            return null;
        }

        User user = new User();

        user.setId( addTicketRequest.getUserId() );

        return user;
    }
}
