package com.java.planta_criteria.user;

public class UserNotFoundException
        extends RuntimeException {

    public UserNotFoundException(String email) {
        super("Utilisateur introuvable : " + email);
    }
}
