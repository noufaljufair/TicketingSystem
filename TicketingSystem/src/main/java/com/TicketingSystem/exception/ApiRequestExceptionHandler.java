package com.TicketingSystem.exception;

import com.TicketingSystem.configuration.Translator;
import com.TicketingSystem.dto.response.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiRequestExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        System.out.println("!!!");
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
        String message = exception.getRootCause().getMessage();
        List<ErrorModel> errorMessage;

        if(message != null && message.contains("duplicate") && message.contains("email")){
            errorMessage = Arrays.asList(new ErrorModel("email","", Translator.toLocale("error.user.email.inUse")));
        }else{
            errorMessage = Arrays.asList(new ErrorModel("","",message));
        }

        ErrorResponse errorResponse = ErrorResponse.builder().errorMessage(errorMessage).build();
        return new ResponseEntity(errorResponse, HttpStatus.CONFLICT);
    }
}
