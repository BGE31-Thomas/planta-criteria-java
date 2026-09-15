package com.java.planta_criteria.taxref.dto;

public class PlantSearchDto {

    private Long id;
    private String label;

    public PlantSearchDto() {
    }

    public PlantSearchDto(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
