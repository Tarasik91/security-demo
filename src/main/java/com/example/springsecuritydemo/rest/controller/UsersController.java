package com.example.springsecuritydemo.rest.controller;

import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.rest.dto.UserResponse;
import com.example.springsecuritydemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    Logger logger = LoggerFactory.getLogger(UsersController.class);
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<UserResponse>> get() {
        logger.info("Get Users");
        List<User> users = userService.findAll();
        List<UserResponse> response = users
                .stream()
                .map(this::map2UserDto)
                .toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        logger.info("DELETE User #" + id);
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted");
    }

    private UserResponse map2UserDto(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.isEnabled());
    }
}
