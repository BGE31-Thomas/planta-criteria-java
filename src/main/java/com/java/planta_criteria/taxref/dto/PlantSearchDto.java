package com.java.planta_criteria.taxref.dto;

public class PlantSearchDto {

    private Integer id;
    private String nomCompletHtml;

    public PlantSearchDto() {
    }

    public PlantSearchDto(Integer id, String nomCompletHtml) {
        this.id = id;
        this.nomCompletHtml = nomCompletHtml;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomCompletHtml() {
        return nomCompletHtml;
    }

    public void setNomCompletHtml(String nomCompletHtml) {
        this.nomCompletHtml = nomCompletHtml;
    }
}
