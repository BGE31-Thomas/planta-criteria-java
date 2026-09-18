package com.java.planta_criteria.auth;

import com.java.planta_criteria.auth.dto.AuthResponse;
import com.java.planta_criteria.auth.dto.LoginRequest;
import com.java.planta_criteria.security.JwtService;
import com.java.planta_criteria.user.Users;
import com.java.planta_criteria.user.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthService(
        AuthenticationManager authenticationManager,
        JwtService jwtService,
        UserRepository userRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public AuthResponse login(LoginRequest request) {

        Authentication authentication =
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.email(),
                    request.password()
                )
            );

        UserDetails userDetails =
            (UserDetails) authentication.getPrincipal();

        Users user =
            userRepository.findByEmail(request.email())
                .orElseThrow();

        String token =
            jwtService.generateToken(userDetails);

        return new AuthResponse(
            token,
            user.getId(),
            user.getEmail(),
            user.getUsername()
        );
    }
}