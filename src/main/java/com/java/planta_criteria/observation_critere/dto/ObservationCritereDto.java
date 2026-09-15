package com.java.planta_criteria.observation_critere.dto;

import java.util.List;

import com.java.planta_criteria.critere.dto.CritereDto;
import com.java.planta_criteria.image.dto.ImageDto;
import com.java.planta_criteria.observation.dto.ObservationDto;
import com.java.planta_criteria.statut.dto.StatutDto;

public class ObservationCritereDto {

    private Long id;
    private ObservationDto observation;
    private CritereDto critere;
    private StatutDto statut;

    private List<ImageDto> images;

    public ObservationCritereDto() {
    }

    public ObservationCritereDto(Long id, ObservationDto observation, CritereDto critere, StatutDto statut, List<ImageDto> images) {
        this.id = id;
        this.observation = observation;
        this.critere = critere;
        this.statut = statut;
        this.images = images;
    }

    public Long getId() {
        return id;
    }   

    public void setId(Long id) {
        this.id = id;
    }

    public ObservationDto getObservation() {
        return observation;
    }

    public void setObservation(ObservationDto observation) {
        this.observation = observation;
    }   

    public CritereDto getCritere() {
        return critere;
    }

    public void setCritere(CritereDto critere) {
        this.critere = critere;
    }

    public StatutDto getStatut() {
        return statut;
    }

    public void setStatut(StatutDto statut) {
        this.statut = statut;
    }


    public List<ImageDto> getImages() {
        return images;
    }

    

}
