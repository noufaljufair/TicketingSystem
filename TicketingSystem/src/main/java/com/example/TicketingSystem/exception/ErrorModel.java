package com.example.TicketingSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ErrorModel{
    private String fieldName;
    private Object rejectedValue;
    private String message;
}
