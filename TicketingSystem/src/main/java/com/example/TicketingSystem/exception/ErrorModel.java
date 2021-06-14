package com.example.TicketingSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorModel extends RuntimeException{
    private String fieldName;
    private Object rejectedValue;
    private String message;
}
