package com.java.planta_criteria.auth.dto;

public record AuthResponse(
        String token,
        Long id,
        String email,
        String username
) {
}