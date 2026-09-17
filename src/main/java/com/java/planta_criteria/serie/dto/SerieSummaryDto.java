package com.java.planta_criteria.serie.dto;

import java.time.LocalDate;

public record SerieSummaryDto(
    Integer id,
    String intitule,
    String lieu,
    LocalDate date
) {}


