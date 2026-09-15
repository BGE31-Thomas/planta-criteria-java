package com.java.planta_criteria.user.dto;

import java.util.Set;

public class UserDto {

    private Long id;
    private String email;
    private String username;
    private Set<String> roles;
    private boolean verified;

    public UserDto() {
    }

    public UserDto(Long id, String email, String username, Set<String> roles, boolean verified) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.roles = roles;
        this.verified = verified;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Set<String> getRoles() {
        return roles;
    }
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

}
