package com.java.planta_criteria.serie.dto;

import com.java.planta_criteria.observation.dto.ObservationDto;

public record SerieObservationDto(
    SerieSearchDto serie,
    ObservationDto observation
) {}
