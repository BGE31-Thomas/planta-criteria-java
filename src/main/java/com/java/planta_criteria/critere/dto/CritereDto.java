package com.java.planta_criteria.critere.dto;

import java.util.List;

import com.java.planta_criteria.image.dto.ImageDto;
import com.java.planta_criteria.source.dto.SourceDto;

public class CritereDto {

    private Long id;
    private String organe;
    private String description;

    private SourceDto source;
    private List<ImageDto> images;
    
    public CritereDto() {
    }

    public CritereDto(Long id, String organe, String description, SourceDto source, List<ImageDto> images) {
        this.id = id;
        this.organe = organe;
        this.description = description;
        this.source = source;
        this.images = images;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
}
