package com.java.planta_criteria.critere;

public class CritereNotFoundException extends RuntimeException {

    public CritereNotFoundException(Integer id) {
        super("Critère introuvable : " + id);
    }
}
