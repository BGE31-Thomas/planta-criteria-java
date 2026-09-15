package com.java.planta_criteria.auth;

public class EmailAlreadyUsedException
        extends RuntimeException {

    public EmailAlreadyUsedException(String email) {
        super("Cette adresse email est déjà utilisée : " + email);
    }
}
