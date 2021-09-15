package com.TicketingSystem.dto.request;

import com.TicketingSystem.model.enums.Category;
import com.TicketingSystem.model.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class AddTicketRequest {
    @NotBlank(message = "{error.ticket.subject.required}")
    @Size(min = 2, max = 30, message ="{error.ticket.subject.size}")
    private String subject;

    @NotBlank(message = "{error.ticket.description.required}")
    @Size(min = 10, message ="{error.ticket.description.size}")
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{error.ticket.category.required}")
    private Category category;

    private long userId;
}
