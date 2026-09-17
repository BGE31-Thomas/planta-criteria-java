package com.java.planta_criteria.image.dto;

public class ImageDto {

    private Integer id;
    private String chemin;
    private String auteur;
    private String description;


    public ImageDto() {
    }

    public ImageDto(Integer id, String chemin, String auteur, String description) {
        this.id = id;
        this.chemin = chemin;
        this.auteur = auteur;
        this.description = description;
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getChemin() {
        return chemin;
    }
    public void setChemin(String chemin) {
        this.chemin = chemin;
    }
    public String getAuteur() {
        return auteur;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // getters/setters
}
