package com.java.planta_criteria.taxref.dto;

import java.util.List;

import com.java.planta_criteria.critere.dto.CritereDto;

public class PlantDto {

    private Long id;
    private String nom;
    private String lbNom;
    private String nomCompletHtml;
    private String famille;

    private List<TaxrefSummaryDto> synonymes;
    private List<CritereDto> criteres;

    public PlantDto() {
    }  

    public PlantDto(Long id, String nom, String lbNom, String nomCompletHtml, String famille, List<TaxrefSummaryDto> synonymes, List<CritereDto> criteres) {
        this.id = id;
        this.nom = nom;
        this.lbNom = lbNom;
        this.nomCompletHtml = nomCompletHtml;
        this.famille = famille;
        this.synonymes = synonymes;
        this.criteres = criteres;
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

    public String getNomCompletHtml() {
        return nomCompletHtml;
    }

    public void setNomCompletHtml(String nomCompletHtml) {
        this.nomCompletHtml = nomCompletHtml;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public List<TaxrefSummaryDto> getSynonymes() {
        return synonymes;
    }

    public List<CritereDto> getCriteres() {
        return criteres;
    }


}
