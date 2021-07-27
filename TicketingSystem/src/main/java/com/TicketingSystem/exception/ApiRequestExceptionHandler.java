package com.TicketingSystem.exception;

import com.TicketingSystem.configuration.Translator;
import com.TicketingSystem.dto.response.ApiResponse;
import com.TicketingSystem.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiRequestExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<FieldErrorModel> errorMessage = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
            new FieldErrorModel(error.getField(), error.getRejectedValue(), error.getDefaultMessage()))
                .distinct()
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder().errorMessage(errorMessage).build();

        return new ResponseEntity(new ApiResponse(false, errorResponse), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
        String message = exception.getRootCause().getMessage();
        List<FieldErrorModel> errorMessage;

        if(message != null && message.contains("duplicate") && message.contains("email")){
            errorMessage = Arrays.asList(new FieldErrorModel("email","", Translator.toLocale("error.user.email.inUse")));
        }else{
            errorMessage = Arrays.asList(new FieldErrorModel("","",message));
        }

        ErrorResponse errorResponse = ErrorResponse.builder().errorMessage(errorMessage).build();
        return new ResponseEntity(new ApiResponse(false, errorResponse), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleBadCredentialsException(BadCredentialsException exception) {
        ApiResponse response = new ApiResponse(false, exception.getMessage());
        return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity handleInvalidFormatException(InvalidFormatException httpMessageNotReadableException){
        String errorMessage = Translator.toLocale("error.invalidEnumValue");
        return new ResponseEntity(new ApiResponse(false, errorMessage), HttpStatus.BAD_REQUEST);
    }
}
