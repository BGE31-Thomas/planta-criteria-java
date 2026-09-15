package com.java.planta_criteria.observation.dto;

import java.util.Date;
import java.util.List;

import com.java.planta_criteria.observation_critere.dto.ObservationCritereDto;

public class ObservationDto {

    private Long id;
    private Date date;
    private String lieu;

    private List<ObservationCritereDto> observationsCritere;

    public ObservationDto() {
    }

    public ObservationDto(Long id, Date date, String lieu, List<ObservationCritereDto> observationsCritere) {
        this.id = id;
        this.date = date;
        this.lieu = lieu;
        this.observationsCritere = observationsCritere;
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

    public List<ObservationCritereDto> getObservationsCritere() {
        return observationsCritere;
    }

}
