package com.TicketingSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class                FieldErrorModel {
    private String fieldName;
    private Object rejectedValue;
    private String message;
}
