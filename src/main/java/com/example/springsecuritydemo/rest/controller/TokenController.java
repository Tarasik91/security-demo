package com.example.springsecuritydemo.rest.controller;

import com.example.springsecuritydemo.config.JwtTokenProvider;
import com.example.springsecuritydemo.rest.dto.TokenRequest;
import com.example.springsecuritydemo.rest.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TokenController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody TokenRequest request) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        Map<String, Object> map = new HashMap<>();
        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        map.put("role", roles);
        String token = jwtTokenProvider.generateToken(authentication, map);
        TokenResponse response = new TokenResponse(token);
        return ResponseEntity.ok(response);
    }
}
