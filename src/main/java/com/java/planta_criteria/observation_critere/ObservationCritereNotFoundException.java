package com.java.planta_criteria.observation_critere;

public class ObservationCritereNotFoundException
        extends RuntimeException {

    public ObservationCritereNotFoundException(Integer id) {
        super("ObservationCritere introuvable : " + id);
    }
}
