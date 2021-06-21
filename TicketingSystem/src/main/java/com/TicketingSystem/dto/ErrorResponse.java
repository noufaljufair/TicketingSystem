package com.TicketingSystem.dto;

import com.TicketingSystem.exception.ErrorModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private List<ErrorModel> errorMessage;
}