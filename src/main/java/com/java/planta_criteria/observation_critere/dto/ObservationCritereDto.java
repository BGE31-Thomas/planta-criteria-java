package com.java.planta_criteria.observation_critere;

import com.java.planta_criteria.critere.CritereDto;
import com.java.planta_criteria.image.ImageDto;
import com.java.planta_criteria.observation.ObservationSummaryDto;
import com.java.planta_criteria.statut.StatutDto;

import java.util.List;

public record ObservationCritereDto(
    Integer id,
    ObservationSummaryDto observation,
    CritereDto critere,
    StatutDto statut,
    List<ImageDto> images
) {}

