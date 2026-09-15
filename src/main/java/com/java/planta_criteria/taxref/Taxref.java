package com.java.planta_criteria.taxref;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.java.planta_criteria.critere.Critere;

@Entity
@Table(name = "taxref")
public class Taxref {

    @Id
    @Column(name = "cd_nom")
    private Long cdNom;

    @Column(name = "cd_ref")
    private Long cdRef;

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
    @JoinColumn(name = "cd_ref", referencedColumnName = "cd_nom")
    private Taxref nomValide;

    @OneToMany(mappedBy = "nomValide")
    private List<Taxref> synonymes;


    public Long getCdNom() {
        return cdNom;
    }

    public void setCdNom(Long cdNom) {
        this.cdNom = cdNom;
    }

    public Long getCdRef() {
        return cdRef;
    }

    public void setCdRef(Long cdRef) {
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
}
