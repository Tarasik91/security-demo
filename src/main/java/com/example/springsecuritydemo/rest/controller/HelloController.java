package com.example.springsecuritydemo.rest.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")

    public String sayHello() {
        return "First hello world app integrated with AWS. @TarasShurhot";
    }
}
