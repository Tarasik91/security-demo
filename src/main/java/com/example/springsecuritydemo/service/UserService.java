package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.rest.dto.RegisterUserRequest;

public interface UserService {
    boolean registerUser(RegisterUserRequest user);
}
