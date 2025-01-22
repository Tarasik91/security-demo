package com.example.springsecuritydemo.rest.controller;

import com.example.springsecuritydemo.exception.RegisterUserException;
import com.example.springsecuritydemo.rest.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiError> handle(MethodArgumentNotValidException exception) {
        //you will get all javax failed validation, can be more than one
        //so you can return the set of error messages or just the first message
        //    String errorMessage = new ArrayList<>(exception.getConstraintViolations()).get(0).getMessage();
        ApiError apiError = new ApiError(exception.getFieldError().getDefaultMessage(), 1000);
        return new ResponseEntity<>(apiError, null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handle(RegisterUserException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), 1000);
        return new ResponseEntity<>(apiError, null, HttpStatus.BAD_REQUEST);
    }
}
