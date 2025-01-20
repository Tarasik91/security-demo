package com.example.springsecuritydemo.rest.dto;

public record UserResponse(String username, String firstName, String lastName, String email, boolean isEnabled) {
}
