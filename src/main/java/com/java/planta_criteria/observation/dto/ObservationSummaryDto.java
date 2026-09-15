package com.java.planta_criteria.observation.dto;

import java.time.LocalDateTime;

public class ObservationSummaryDto {

    private Long id;
    private LocalDateTime date;
    private String lieu;

    public ObservationSummaryDto() {
    }

    public ObservationSummaryDto(Long id, LocalDateTime date, String lieu) {
        this.id = id;
        this.date = date;
        this.lieu = lieu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
