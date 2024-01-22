package ru.taxiservice.authservice.service;

import ru.taxiservice.authservice.dto.RegistrationRequest;
import ru.taxiservice.authservice.dto.UserDto;
import ru.taxiservice.authservice.entity.User;

import java.util.Optional;


public interface UserService {

    UserDto register(RegistrationRequest registrationRequest);

    User findByUsername(String username);

    Optional<User> findById(Long id);


}
