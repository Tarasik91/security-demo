package com.example.springsecuritydemo.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class RegisterUserRequest {

    @Size(min = 3)
    @NotBlank(message = "UserName is required")
    private String username;

    @Size(min = 5)
    @NotBlank(message = "Email is required")
    private String email;
    @Size(min = 5)
    @NotBlank(message = "Password is required")
    private String password;
    @Size(min = 2)
    @NotBlank(message = "First name is required")
    private String firstName;
    @Size(min = 3)
    @NotBlank(message = "Last name is required")
    private String lastName;

    private LocalDate birthDate;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "RegisterUserRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password=*****" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
