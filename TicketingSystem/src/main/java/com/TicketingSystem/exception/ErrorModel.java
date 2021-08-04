package com.TicketingSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ErrorModel {
    private String name;
    private Object rejectedValue;
    private String message;

    private String reason;

    public ErrorModel(String message){
        this.message = message;
    }

    public ErrorModel(String message, String reason){
        this.message = message;
        this.reason = reason;
    }

    public ErrorModel(String name, Object rejectedValue, String message){
        this.name = name;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }
}
