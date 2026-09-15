package com.java.planta_criteria.observation;

import org.springframework.stereotype.Component;

import com.java.planta_criteria.observation.dto.ObservationDto;
import com.java.planta_criteria.observation.dto.ObservationSummaryDto;
import com.java.planta_criteria.observation_critere.ObservationCritereMapper;

import java.time.ZoneId;
import java.util.Date;

@Component
public class ObservationMapper {

    private final ObservationCritereMapper observationCritereMapper;

    public ObservationMapper(
        ObservationCritereMapper observationCritereMapper
    ) {
        this.observationCritereMapper = observationCritereMapper;
    }

    public ObservationDto toSearchDto(Observation observation) {

        return new ObservationDto(
            observation.getId(),
            toDate(observation),
            observation.getLieu(),
            observation.getObservationsCritere()
                .stream()
                .map(observationCritereMapper::toSearchDto)
                .toList()
        );
    }

    public ObservationSummaryDto toSummaryDto(
        Observation observation
    ) {

        return new ObservationSummaryDto(
            observation.getId(),
            toDate(observation),
            observation.getLieu()
        );
    }

    private Date toDate(Observation observation) {

        if (observation.getDateHeure() == null) {
            return null;
        }

        return Date.from(
            observation.getDateHeure()
                .atZone(ZoneId.systemDefault())
                .toInstant()
        );
    }
}