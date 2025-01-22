package com.example.springsecuritydemo.exception;

public class RegisterUserException extends RuntimeException {

    public RegisterUserException(String message) {
        super(message);
    }
}
