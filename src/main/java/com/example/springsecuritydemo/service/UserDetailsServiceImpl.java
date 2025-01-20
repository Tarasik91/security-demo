package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.model.Role;
import com.example.springsecuritydemo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        com.example.springsecuritydemo.model.User user = userRepository.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }
        String[] userRoles = user.getRoles().stream().map(Role::getRole).toArray(String[]::new);

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(userRoles)
                .build();
    }

}
