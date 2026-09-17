package com.java.planta_criteria.serie.dto;

import java.time.LocalDate;

public record SerieCreateDto(
    String intitule,
    String lieu,
    LocalDate date
) {}


