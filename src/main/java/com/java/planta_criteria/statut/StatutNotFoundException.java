package com.java.planta_criteria.statut;

public class StatutNotFoundException extends RuntimeException {

    public StatutNotFoundException(Integer id) {
        super("Statut introuvable : " + id);
    }

    public StatutNotFoundException(String libelle) {
        super("Statut introuvable : " + libelle);
    }
}
