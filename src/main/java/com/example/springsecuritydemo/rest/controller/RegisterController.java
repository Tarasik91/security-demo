package com.example.springsecuritydemo.rest.controller;

import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.model.VerificationToken;
import com.example.springsecuritydemo.rest.dto.RegisterUserRequest;
import com.example.springsecuritydemo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@Validated
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserRequest dto, HttpServletRequest request) {
        userService.registerUser(dto);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/registration-confirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token) {

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            throw new IllegalArgumentException("Token is invalid");
        }

        User user = verificationToken.getUser();

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token is expired");
        }

        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        return ResponseEntity.ok("REGISTERED");
    }
}
