package ru.taxiservice.authservice.dto;

public record RegistrationRequest(String username, String password, String firstName, String lastName, String role) {
}
