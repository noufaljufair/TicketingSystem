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
public class AllTicketDto {
    private Long id;
    private String subject;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    private LocalDate createdDate;

    private String clientFirstName;
    private String clientLastName;

    public AllTicketDto(final Long id, final String subject, final Category category, final TicketStatus status, final LocalDateTime createdDate, final String clientFirstName, final String clientLastName) {
        this.id = id;
        this.subject = subject;
        this.category = category;
        this.status = status;
        this.createdDate = createdDate.toLocalDate();
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
    }
}
