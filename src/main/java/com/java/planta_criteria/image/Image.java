package com.java.planta_criteria.image;

import com.java.planta_criteria.critere.Critere;
import com.java.planta_criteria.observation_critere.ObservationCritere;

import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false)
    private String chemin;

    @Column(length = 255)
    private String auteur;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "critere_id")
    private Critere critere;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "observation_critere_id")
    private ObservationCritere observationCritere;

    public Image(){

    }

    public Image(Integer id, String chemin, String auteur, String description, Critere critere, ObservationCritere observationCritere){
        this.id = id;
        this.chemin = chemin;
        this.auteur = auteur;
        this.description = description;
        this.critere = critere;
        this.observationCritere = observationCritere;
    }

    public Integer getId() {
        return id;
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

    public Critere getCritere() {
        return critere;
    }

    public void setCritere(Critere critere) {
        this.critere = critere;
    }

    public ObservationCritere getObservationCritere() {
        return observationCritere;
    }

    public void setObservationCritere(
        ObservationCritere observationCritere
    ) {
        this.observationCritere = observationCritere;
    }
}
