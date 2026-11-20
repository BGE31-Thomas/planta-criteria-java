package com.java.planta_criteria.observation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservationRepository extends JpaRepository<Observation, Integer> {
    boolean existsBySerieIdAndPlanteCdNom(
        Integer serieId,
        Integer planteId
    );
}
