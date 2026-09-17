package com.java.planta_criteria.auth.dto;

public record AuthResponse(
        String token,
        Integer id,
        String email,
        String username
) {
}