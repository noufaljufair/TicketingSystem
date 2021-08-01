package com.TicketingSystem.dto.response;

import com.TicketingSystem.model.enums.Category;
import com.TicketingSystem.model.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
public class AllTicketDto {
    private Long id;
    private String subject;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    private LocalDateTime createdDate;

    private String clientFirstName;
    private String clientLastName;
}
