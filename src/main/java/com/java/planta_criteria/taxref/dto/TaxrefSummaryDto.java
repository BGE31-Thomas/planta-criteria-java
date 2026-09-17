package com.java.planta_criteria.taxref.dto;

public class TaxrefSummaryDto {

    private Integer id;
    private String nom;
    private String lbNom;

    public TaxrefSummaryDto() {
    }

    public TaxrefSummaryDto(Integer id, String nom, String lbNom) {
        this.id = id;
        this.nom = nom;
        this.lbNom = lbNom;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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
