package com.java.planta_criteria.taxref.dto;

public class PlantSearchDto {

    private Integer id;
    private String lbNom;

    public PlantSearchDto() {
    }

    public PlantSearchDto(Integer id, String lbNom) {
        this.id = id;
        this.lbNom = lbNom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLbNom() {
        return lbNom;
    }

    public void setLbNom(String lbNom) {
        this.lbNom = lbNom;
    }
}
