package ru.taxiservice.authservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.taxiservice.authservice.dto.RegistrationRequest;
import ru.taxiservice.authservice.dto.UserDto;
import ru.taxiservice.authservice.entity.Role;
import ru.taxiservice.authservice.entity.User;
import ru.taxiservice.authservice.mapper.RegistrationMapper;
import ru.taxiservice.authservice.mapper.UserMapper;
import ru.taxiservice.authservice.repository.UserRepository;
import ru.taxiservice.authservice.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final RegistrationMapper registrationMapper;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder, RegistrationMapper registrationMapper,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.registrationMapper = registrationMapper;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto register(RegistrationRequest registrationRequest) {
        User user = registrationMapper.toEntity(registrationRequest);
        String role = registrationRequest.role();
        Role userRole = new Role();
        userRole.setName(role);
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(registrationRequest.password()));

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> user = userRepository.findById(id);


        return user;
    }


}
