package com.java.planta_criteria.serie;

import org.springframework.stereotype.Component;

import com.java.planta_criteria.serie.dto.SerieDto;
import com.java.planta_criteria.observation.ObservationMapper;

import com.java.planta_criteria.serie.Serie;

import java.time.ZoneId;
import java.util.Date;

@Component
public class SerieMapper {

    private final ObservationMapper observationMapper;

    public SerieMapper(
        ObservationMapper observationMapper
    ) {
        this.observationMapper = observationMapper;
    }

    public SerieDto toSearchDto(Serie serie) {

        return new SerieDto(
            serie.getId(),
            serie.getIntitule(),
            serie.getLieu(),
            serie.getDate()
        );
    }

    
}
