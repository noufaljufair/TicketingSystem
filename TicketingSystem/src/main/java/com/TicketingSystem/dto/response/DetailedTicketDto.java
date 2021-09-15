package com.TicketingSystem.dto.response;

import com.TicketingSystem.model.enums.Category;
import com.TicketingSystem.model.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
public class DetailedTicketDto {
    private String subject;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;
    private String clientFirstName;
    private String clientLastName;

    public DetailedTicketDto(final String subject, final String description, final Category category, final TicketStatus status, final LocalDateTime createdDate, final LocalDateTime lastModifiedDate, final String clientFirstName, final String clientLastName) {
        this.subject = subject;
        this.description = description;
        this.category = category;
        this.status = status;
        this.createdDate = createdDate.toLocalDate();
        this.lastModifiedDate = lastModifiedDate.toLocalDate();
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
    }
}
