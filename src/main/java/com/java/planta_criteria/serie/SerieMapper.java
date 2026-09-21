package com.java.planta_criteria.serie;

import java.util.List;

import org.springframework.stereotype.Component;

import com.java.planta_criteria.serie.dto.SerieDto;
import com.java.planta_criteria.serie.dto.SerieSearchDto;
import com.java.planta_criteria.observation.dto.ObservationDto;



@Component
public class SerieMapper {

    public SerieSearchDto toSearchDto(Serie serie) {
        return new SerieSearchDto(
            serie.getId(),
            serie.getIntitule(),
            serie.getLieu(),
            serie.getDate()
        );
    }

    public SerieDto toDto(Serie serie, List<ObservationDto> observations) {
        return new SerieDto(
            serie.getId(),
            serie.getIntitule(),
            serie.getLieu(),
            serie.getDate(),
            observations
        );
    }
}
