package com.TicketingSystem.dto.response;

import com.TicketingSystem.model.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@Getter
public class AbstractTicketDto {
    private Long id;
    private String subject;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}
