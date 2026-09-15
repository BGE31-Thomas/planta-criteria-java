package com.java.planta_criteria.observation;

public class ObservationNotFoundException extends RuntimeException {

    public ObservationNotFoundException(Long id) {
        super("Observation introuvable : " + id);
    }
}
