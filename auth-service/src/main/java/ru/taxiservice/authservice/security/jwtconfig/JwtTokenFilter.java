package ru.taxiservice.authservice.security.jwtconfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.taxiservice.authservice.security.JwtUserDetailsService;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    private final JwtUserDetailsService jwtUserService;

    @Autowired
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, JwtUserDetailsService jwtUserService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtUserService = jwtUserService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            if (jwt.isBlank()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer header");
            } else {
                try {
                    String username = jwtTokenProvider.validateTokenAndRetrieveClaim(jwt);
                    UserDetails jwtUser = jwtUserService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(jwtUser,
                                    jwtUser.getPassword(),
                                    jwtUser.getAuthorities());
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                } catch (JwtAuthenticationException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid token");
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
