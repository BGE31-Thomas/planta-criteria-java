package com.java.planta_criteria.observation.dto;

import com.java.planta_criteria.observation_critere.dto.ObservationCritereDto;
import com.java.planta_criteria.serie.dto.SerieSearchDto;
import com.java.planta_criteria.taxref.dto.PlantSearchDto;

import java.util.List;

public record ObservationDto(
    Integer id,
    SerieSearchDto serie,
    PlantSearchDto plante,
    List<ObservationCritereDto> observationsCritere
) {}



