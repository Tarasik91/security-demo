package com.example.springsecuritydemo.repository;

import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);

}
