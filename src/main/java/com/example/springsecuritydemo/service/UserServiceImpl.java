package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.event.OnRegistrationCompleteEvent;
import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.model.VerificationToken;
import com.example.springsecuritydemo.repository.UserRepository;
import com.example.springsecuritydemo.repository.VerificationTokenRepository;
import com.example.springsecuritydemo.rest.dto.RegisterUserRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final ApplicationEventPublisher eventPublisher;

    private final VerificationTokenRepository tokenRepository;

    private final HttpServletRequest request;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ApplicationEventPublisher eventPublisher, VerificationTokenRepository tokenRepository, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventPublisher = eventPublisher;
        this.tokenRepository = tokenRepository;
        this.request = request;
    }

    @Override
    public boolean registerUser(RegisterUserRequest requestDto) {
        validateUserRequest(requestDto);
        //TODO
        //validateForExistUser(request);
        User user = map2User(requestDto);
        userRepository.save(user);

        String appUrl = request.getContextPath();

        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user,
                request.getLocale(), appUrl));

        return false;
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
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
