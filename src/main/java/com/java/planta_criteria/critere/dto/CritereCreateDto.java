package com.java.planta_criteria.critere.dto;

public class CritereCreateDto {
    private String organe;
    private String description;

    private Integer source_id;

    private Integer plante_id;

    public CritereCreateDto(){

    }

    public CritereCreateDto(String organe, String description, Integer source_id, Integer plante_id){
        this.organe = organe;
        this.description = description;
        this.source_id = source_id;
        this.plante_id = plante_id;
    }
    
    public String getOrgane(){
        return organe;
    }

    public String getDescription(){
        return description;
    }

    public Integer getSource_id(){
        return source_id;
    }

    public Integer getPlante_id(){
        return plante_id;
    }

    public void setOrgane(String organe) {
        this.organe = organe;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setSource_id(Integer source_id){
        this.source_id = source_id;
    }

    public void setPlante_id(Integer plante_id){
        this.plante_id = plante_id;
    }

}
