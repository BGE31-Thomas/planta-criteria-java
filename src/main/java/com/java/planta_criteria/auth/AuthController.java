package com.java.planta_criteria.auth;

import com.java.planta_criteria.auth.dto.AuthResponse;
import com.java.planta_criteria.auth.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
        @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(
            authService.login(request)
        );
    }
}
