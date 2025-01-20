package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.rest.dto.RegisterUserRequest;

import java.util.List;

public interface UserService {
    boolean registerUser(RegisterUserRequest user);

    List<User> findAll();
}
