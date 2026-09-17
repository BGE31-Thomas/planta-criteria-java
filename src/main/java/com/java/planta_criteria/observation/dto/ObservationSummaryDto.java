package com.java.planta_criteria.observation.dto;

import java.time.LocalDateTime;

public class ObservationSummaryDto {

    private Integer id;
    private LocalDateTime date;
    private String lieu;

    public ObservationSummaryDto() {
    }

    public ObservationSummaryDto(Integer id, LocalDateTime date, String lieu) {
        this.id = id;
        this.date = date;
        this.lieu = lieu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
