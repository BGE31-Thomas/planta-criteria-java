package com.java.planta_criteria.observation_critere;

public class ObservationCritereNotFoundException
        extends RuntimeException {

    public ObservationCritereNotFoundException(Long id) {
        super("ObservationCritere introuvable : " + id);
    }
}
