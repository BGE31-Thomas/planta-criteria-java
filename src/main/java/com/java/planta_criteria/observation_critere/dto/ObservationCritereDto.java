package com.java.planta_criteria.observation_critere.dto;

import com.java.planta_criteria.critere.dto.CritereDto;
import com.java.planta_criteria.image.dto.ImageDto;
import com.java.planta_criteria.observation.dto.ObservationSummaryDto;
import com.java.planta_criteria.statut.dto.StatutDto;

import java.util.List;

public record ObservationCritereDto(
    Integer id,
    ObservationSummaryDto observation,
    CritereDto critere,
    StatutDto statut,
    List<ImageDto> images
) {}

