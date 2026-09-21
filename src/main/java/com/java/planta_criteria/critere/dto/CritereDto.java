package com.java.planta_criteria.critere.dto;

import java.util.List;

import com.java.planta_criteria.image.dto.ImageDto;
import com.java.planta_criteria.source.dto.SourceDto;

import com.java.planta_criteria.taxref.Taxref;
import com.java.planta_criteria.taxref.dto.PlantDto;

public class CritereDto {

    private Integer id;
    private String organe;
    private String description;

    private SourceDto source;
    private List<ImageDto> images;

    private PlantDto plante;
    
    public CritereDto() {
    }

    public CritereDto(Integer id, String organe, String description, SourceDto source, PlantDto plante, List<ImageDto> images) {
        this.id = id;
        this.organe = organe;
        this.description = description;
        this.source = source;
        this.plante = plante;
        this.images = images;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOrgane() {
        return organe;
    }
    public void setOrgane(String organe) {
        this.organe = organe;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public SourceDto getSource() {
        return source;
    }
    public void setSource(SourceDto source) {
        this.source = source;
    }
    public List<ImageDto> getImages() {
        return images;
    }

    public PlantDto getPlante() {
        return plante;
    }
    public void setPlante(PlantDto plante) {
        this.plante = plante;
    }
}
