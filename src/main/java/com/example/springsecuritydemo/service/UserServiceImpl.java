package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.repository.UserRepository;
import com.example.springsecuritydemo.rest.dto.RegisterUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean registerUser(RegisterUserRequest request) {
        validateUserRequest(request);
        //validateForExistUser(request);
        User user = map2User(request);
        userRepository.save(user);
        return false;
    }

    private void validateUserRequest(RegisterUserRequest request) {
        /*if (request.firstName().isBlank()) {
            throw new IllegalArgumentException();
        }
        if (request.lastName().isBlank()) {
            throw new IllegalArgumentException();
        }
        if (request.username().isBlank()) {
            throw new IllegalArgumentException();
        }
        if (request.password().isBlank()) {
            throw new IllegalArgumentException();
        }*/
    }

    private User map2User(RegisterUserRequest request) {
        var user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }
}
