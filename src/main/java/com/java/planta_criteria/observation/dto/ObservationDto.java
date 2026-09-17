package com.java.planta_criteria.observation.dto;

import com.java.planta_criteria.observation_critere.dto.ObservationCritereDto;
import com.java.planta_criteria.serie.dto.SerieDto;
import com.java.planta_criteria.taxref.dto.PlantDto;

import java.util.List;

public record ObservationDto(
    Integer id,
    SerieDto serie,
    PlantDto plante,
    List<ObservationCritereDto> observationsCritere
) {}



