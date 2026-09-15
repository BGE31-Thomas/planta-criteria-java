package com.java.planta_criteria.source;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.java.planta_criteria.critere.Critere;


@Entity
@Table(name = "source")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String titre;

    @Column(length = 255, nullable = false)
    private String auteur;

    @Column(length = 4, nullable = false)
    private Integer annee;

    @OneToMany(
        mappedBy = "source",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Critere> criteres = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public Integer getAnnee() {
        return annee;
    }

    public List<Critere> getCriteres() {
        return criteres;
    }   

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public void addCritere(Critere critere) {
        criteres.add(critere);
        critere.setSource(this);
    }

    public void removeCritere(Critere critere) {
        criteres.remove(critere);
        critere.setSource(null);
    }


}
