package com.java.planta_criteria.observation;

public class ObservationNotFoundException extends RuntimeException {

    public ObservationNotFoundException(Integer id) {
        super("Observation introuvable : " + id);
    }
}
