package com.java.planta_criteria.observation_critere;

import org.springframework.stereotype.Component;

import com.java.planta_criteria.critere.CritereMapper;
import com.java.planta_criteria.image.ImageMapper;
import com.java.planta_criteria.observation.ObservationMapper;
import com.java.planta_criteria.observation_critere.dto.ObservationCritereDto;
import com.java.planta_criteria.statut.StatutMapper;

@Component
public class ObservationCritereMapper {

    private final ImageMapper imageMapper;
    private final CritereMapper critereMapper;
    private final ObservationMapper observationMapper;
    private final StatutMapper statutMapper;

    public ObservationCritereMapper(
        ImageMapper imageMapper,
        CritereMapper critereMapper,
        ObservationMapper observationMapper,
        StatutMapper statutMapper
    ) {
        this.imageMapper = imageMapper;
        this.critereMapper = critereMapper;
        this.observationMapper = observationMapper;
        this.statutMapper = statutMapper;
    }

    public ObservationCritereDto toSearchDto(
        ObservationCritere observationCritere
    ) {

        return new ObservationCritereDto(
            observationCritere.getId(),

            observationMapper.toSummaryDto(
                observationCritere.getObservation()
            ),

            critereMapper.toSearchDto(
                observationCritere.getCritere()
            ),

            statutMapper.toSearchDto(
                observationCritere.getStatut()
            ),

            observationCritere.getImages()
                .stream()
                .map(imageMapper::toSearchDto)
                .toList()
        );
    }
}