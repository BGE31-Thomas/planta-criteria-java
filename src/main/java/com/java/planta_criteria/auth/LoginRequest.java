package com.java.planta_criteria.auth.dto;

public record LoginRequest(
    String email,
    String password
) {
}
