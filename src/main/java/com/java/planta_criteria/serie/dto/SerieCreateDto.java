package com.java.planta_criteria.serie.dto;

import java.time.LocalDate;

import com.java.planta_criteria.user.dto.UserDto;


public class SerieCreateDto{

    private String intitule;
    private String lieu;
    private LocalDate date;
    private UserDto user;

    public SerieCreateDto(){

    }

    public SerieCreateDto(String intitule, String lieu, LocalDate date, UserDto user){

        this.intitule = intitule;
        this.lieu = lieu;
        this.date = date;
        this.user = user;

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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}


