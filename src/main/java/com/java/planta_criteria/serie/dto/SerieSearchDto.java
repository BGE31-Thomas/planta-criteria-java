package com.java.planta_criteria.serie.dto;

import java.time.LocalDate;

public record SerieSearchDto(
    Integer id,
    String intitule,
    String lieu,
    LocalDate date
) {}
