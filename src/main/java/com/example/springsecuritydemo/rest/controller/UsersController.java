package com.example.springsecuritydemo.rest.controller;

import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.rest.dto.UserResponse;
import com.example.springsecuritydemo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<UserResponse>> get() {
        List<User> users = userService.findAll();
        List<UserResponse> response = users
                .stream()
                .map(this::map2UserDto)
                .toList();
        return ResponseEntity.ok(response);
    }

    private UserResponse map2UserDto(User user) {
        return new UserResponse(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.isEnabled());
    }
}
