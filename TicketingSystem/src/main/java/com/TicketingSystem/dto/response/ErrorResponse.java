package com.TicketingSystem.dto.response;

import com.TicketingSystem.exception.FieldErrorModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private List<FieldErrorModel> errorMessage;
}