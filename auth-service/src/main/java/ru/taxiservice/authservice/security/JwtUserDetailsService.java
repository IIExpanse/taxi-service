package ru.taxiservice.authservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.taxiservice.authservice.entity.User;
import ru.taxiservice.authservice.repository.UserRepository;
import ru.taxiservice.authservice.security.jwtconfig.JwtUser;
import ru.taxiservice.authservice.security.jwtconfig.JwtUserFactory;

import java.util.Collection;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);

        return jwtUser;
    }

    public Collection<? extends GrantedAuthority> getUserRole(String username) {
        return loadUserByUsername(username).getAuthorities();
    }

}
