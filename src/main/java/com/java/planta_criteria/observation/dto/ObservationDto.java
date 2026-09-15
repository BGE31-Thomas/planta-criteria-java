package com.java.planta_criteria.observation.dto;

import java.util.List;

import com.java.planta_criteria.observation_critere.dto.ObservationCritereDto;

import java.time.LocalDateTime;

public class ObservationDto {
    private Long id;
    private LocalDateTime dateHeure;
    private String lieu;
    private List<ObservationCritereDto> observationsCritere;

    public ObservationDto() {
    }

    public ObservationDto(
        Long id,
        LocalDateTime dateHeure,
        String lieu,
        List<ObservationCritereDto> observationsCritere
    ) {
        this.id = id;
        this.dateHeure = dateHeure;
        this.lieu = lieu;
        this.observationsCritere = observationsCritere;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public List<ObservationCritereDto> getObservationsCritere() {
        return observationsCritere;
    }
}
