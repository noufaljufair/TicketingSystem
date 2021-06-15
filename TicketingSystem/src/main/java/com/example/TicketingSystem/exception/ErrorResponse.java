package com.example.TicketingSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private List<ErrorModel> errorMessage;
}