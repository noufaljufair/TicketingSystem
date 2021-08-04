package com.TicketingSystem.dto.response;

import com.TicketingSystem.exception.ErrorModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private List<ErrorModel> errorMessage;
}