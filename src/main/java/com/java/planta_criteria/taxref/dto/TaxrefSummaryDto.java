package com.java.planta_criteria.taxref.dto;

public class TaxrefSummaryDto {

    private Long id;
    private String nom;
    private String lbNom;

    public TaxrefSummaryDto() {
    }

    public TaxrefSummaryDto(Long id, String nom, String lbNom) {
        this.id = id;
        this.nom = nom;
        this.lbNom = lbNom;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getLbNom() {
        return lbNom;
    }
    public void setLbNom(String lbNom) {
        this.lbNom = lbNom;
    }
}
