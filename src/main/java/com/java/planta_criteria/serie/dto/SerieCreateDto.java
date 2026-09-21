package com.java.planta_criteria.serie.dto;

import java.time.LocalDate;


public class SerieCreateDto{

    private String intitule;
    private String lieu;
    private LocalDate date;

    public SerieCreateDto(){

    }

    public SerieCreateDto(String intitule, String lieu, LocalDate date){

        this.intitule = intitule;
        this.lieu = lieu;
        this.date = date;

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

    public void setIntitule(String intitule){
        this.intitule = intitule;
    }

    public void setLieu(String lieu){
        this.lieu = lieu;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }
}


