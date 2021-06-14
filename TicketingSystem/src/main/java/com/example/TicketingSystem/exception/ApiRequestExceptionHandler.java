package com.example.TicketingSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiRequestExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(MethodArgumentNotValidException exception){
        List<ErrorModel> errorMessage = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new ErrorModel(error.getField(), error.getRejectedValue(), error.getDefaultMessage()))
                .distinct()
                .collect(Collectors.toList());
        return ErrorResponse.builder().errorMessage(errorMessage).build();
    }
}
