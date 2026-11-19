package com.java.planta_criteria.serie.dto;

import java.time.LocalDate;

public class SerieObservationCreateDto {

    private String intitule;
    private String lieu;
    private LocalDate date;
    private Integer planteId;

    public SerieObservationCreateDto(){

    }

    public SerieObservationCreateDto(String intitule, String lieu, LocalDate date, Integer planteId){

        this.intitule = intitule;
        this.lieu = lieu;
        this.date = date;
        this.planteId = planteId;

    }

    public String getIntitule(){
        return intitule;
    }

    public String getLieu(){
        return lieu;
    }

    public LocalDate getDate(){
        return date;
    }

    public Integer getPlanteId(){
        return planteId;
    }

    public void setIntitule(String intitule){
        this.intitule = intitule;
    }

    public void setLieu(String lieu){
        this.lieu = lieu;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setPlanteId(Integer planteId){
        this.planteId = planteId;
    }

    
}
