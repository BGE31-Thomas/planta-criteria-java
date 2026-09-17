package com.java.planta_criteria.observation;

import org.springframework.stereotype.Component;

import com.java.planta_criteria.observation.dto.ObservationDto;
import com.java.planta_criteria.observation.dto.ObservationSummaryDto;
import com.java.planta_criteria.observation_critere.ObservationCritereMapper;
import com.java.planta_criteria.serie.SerieMapper;
import com.java.planta_criteria.taxref.TaxrefMapper;

import java.time.ZoneId;
import java.util.Date;

@Component
public class ObservationMapper {

    private final ObservationCritereMapper observationCritereMapper;
    private final TaxrefMapper taxrefMapper;
    private final SerieMapper serieMapper;

    public ObservationMapper(
        ObservationCritereMapper observationCritereMapper,
        TaxrefMapper taxrefMapper,
        SerieMapper serieMapper
    ) {
        this.observationCritereMapper = observationCritereMapper;
        this.taxrefMapper = taxrefMapper;
        this.serieMapper = serieMapper;
    }

    public ObservationDto toSearchDto(Observation observation) {

        return new ObservationDto(
            observation.getId(),
            serieMapper.toSearchDto(observation.getSerie()),
            observation.getPlante() != null ? taxrefMapper.toDto(observation.getPlante()) : null,
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
            observation.getId()
        );
    }

    
}