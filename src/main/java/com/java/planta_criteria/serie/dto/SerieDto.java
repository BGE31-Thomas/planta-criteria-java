package com.java.planta_criteria.serie.dto;

import java.time.LocalDate;
import com.java.planta_criteria.observation.dto.ObservationDto;

import java.util.List;

public record SerieDto(
    Integer id,
    String intitule,
    String lieu,
    LocalDate date,
    List<ObservationDto> observations

) {}


