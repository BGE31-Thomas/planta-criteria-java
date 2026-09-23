package com.java.planta_criteria.serie.dto;

import java.time.LocalDate;

import com.java.planta_criteria.user.dto.UserDto;

public record SerieSearchDto(
    Integer id,
    String intitule,
    String lieu,
    LocalDate date,
    UserDto user
) {}
