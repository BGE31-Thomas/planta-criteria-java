package com.java.planta_criteria.observation.dto;

import java.util.Date;

public class ObservationSummaryDto {

    private Long id;
    private Date date;
    private String lieu;

    public ObservationSummaryDto() {
    }

    public ObservationSummaryDto(Long id, Date date, String lieu) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
