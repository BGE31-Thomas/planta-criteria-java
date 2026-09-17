package com.java.planta_criteria.taxref;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.java.planta_criteria.critere.Critere;

import com.java.planta_criteria.observation.Observation;

@Entity
@Table(name = "taxref")
public class Taxref {

    @Id
    @Column(name = "cd_nom")
    private Integer cdNom;

    @Column(name = "cd_ref")
    private Integer cdRef;

    @Column(length = 255)
    private String famille;

    @Column(name = "nom_complet_html", length = 255)
    private String nomCompletHtml;

    @Column(length = 255)
    private String nom;

    @Column(name = "lb_nom", length = 255)
    private String lbNom;

    @Column(name = "lb_auteur", length = 255)
    private String lbAuteur;

    @OneToMany(mappedBy = "plante")
    private List<Critere> criteres = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "cd_ref",
        referencedColumnName = "cd_nom",
        insertable = false,
        updatable = false
    )
    private Taxref nomValide;

    @OneToMany(mappedBy = "nomValide")
    private List<Taxref> synonymes = new ArrayList<>();

    @OneToMany(mappedBy = "plante")
    private List<Observation> observations = new ArrayList<>();

    public Integer getCdNom() {
        return cdNom;
    }

    public void setCdNom(Integer cdNom) {
        this.cdNom = cdNom;
    }

    public Integer getCdRef() {
        return cdRef;
    }

    public void setCdRef(Integer cdRef) {
        this.cdRef = cdRef;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public String getNomCompletHtml() {
        return nomCompletHtml;
    }

    public void setNomCompletHtml(String nomCompletHtml) {
        this.nomCompletHtml = nomCompletHtml;
    }

    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public Taxref getNomValide() {
        return nomValide;
    }

    public void setNomValide(Taxref nomValide) {
        this.nomValide = nomValide;
    }

    public List<Taxref> getSynonymes() {
        return synonymes;
    }

    public void addSynonyme(Taxref synonyme) {
        synonymes.add(synonyme);
        synonyme.setNomValide(this);
    }

    public void removeSynonyme(Taxref synonyme) {
        synonymes.remove(synonyme);
        synonyme.setNomValide(null);
    }

    public String getLbNom() {
        return lbNom;
    }

    public void setLbNom(String lbNom) {
        this.lbNom = lbNom;
    }

    public String getLbAuteur() {
        return lbAuteur;
    }

    public void setLbAuteur(String lbAuteur) {
        this.lbAuteur = lbAuteur;
    }

    public List<Critere> getCriteres() {
        return criteres;
    }

     public void addCritere(Critere critere) {
        criteres.add(critere);
        critere.setPlante(this);
    }

    public void removeCritere(Critere critere) {
        criteres.remove(critere);
        critere.setPlante(null);
    }

    public List<Observation> getObservations() {
        return observations;
    }

    public void addObservation(Observation observation) {
        if (!observations.contains(observation)) {
            observations.add(observation);
            observation.setPlante(this);
        }
    }

    public void removeObservation(Observation observation) {
        if (observations.remove(observation)) {
            if (observation.getPlante() == this) {
                observation.setPlante(null);
            }
        }
    }
}
