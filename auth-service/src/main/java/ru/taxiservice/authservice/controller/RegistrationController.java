package ru.taxiservice.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.taxiservice.authservice.dto.RegistrationRequest;
import ru.taxiservice.authservice.dto.UserDto;
import ru.taxiservice.authservice.service.UserService;

@RestController
@RequestMapping(value = "/reg")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity register(@RequestBody RegistrationRequest request) {
        UserDto userDto = userService.register(request);
        return ResponseEntity.ok(userDto);

    }
}
