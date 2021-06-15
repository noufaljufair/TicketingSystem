package com.example.TicketingSystem.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiRequestExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ErrorModel> errorMessage = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
            new ErrorModel(error.getField(), error.getRejectedValue(), error.getDefaultMessage()))
                .distinct()
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder().errorMessage(errorMessage).build();

        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
}
}
