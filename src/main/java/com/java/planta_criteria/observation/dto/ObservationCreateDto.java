package com.java.planta_criteria.observation.dto;

import java.time.LocalDateTime;

public class ObservationCreateDto {

    private LocalDateTime dateHeure;
    private String lieu;
    private Long planteId;

    public ObservationCreateDto() {
    }

    public ObservationCreateDto(
        LocalDateTime dateHeure,
        String lieu,
        Long planteId
    ) {
        this.dateHeure = dateHeure;
        this.lieu = lieu;
        this.planteId = planteId;
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

    public Long getPlanteId() {
        return planteId;
    }

    public void setPlanteId(Long planteId) {
        this.planteId = planteId;
    }
}
