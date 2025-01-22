package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.model.VerificationToken;
import com.example.springsecuritydemo.rest.dto.RegisterUserRequest;

import java.util.List;

public interface UserService {
    boolean registerUser(RegisterUserRequest user);

    void createVerificationToken(User user, String token);

    List<User> findAll();

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    VerificationToken getVerificationToken(String VerificationToken);

    User findByUsername(String username);

    User findByEmail(String email);

    void deleteUser(Long id);
}
