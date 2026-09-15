package com.java.planta_criteria.auth.dto;

public class PasswordResetDto {

    private String token;
    private String password;

    public PasswordResetDto() {
    }   

    public PasswordResetDto(String token, String password) {
        this.token = token;
        this.password = password;
    }
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // getters/setters
}
