package com.java.planta_criteria.critere;

public class CritereNotFoundException extends RuntimeException {

    public CritereNotFoundException(Long id) {
        super("Critère introuvable : " + id);
    }
}
